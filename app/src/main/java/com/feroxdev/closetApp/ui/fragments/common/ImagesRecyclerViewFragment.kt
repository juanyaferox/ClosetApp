package com.feroxdev.closetApp.ui.fragments.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.databinding.FragmentRecyclerviewBinding
import com.feroxdev.closetApp.ui.adapters.ImageAdapter
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory
import com.feroxdev.closetApp.utilities.Helper
import com.google.android.material.bottomnavigation.BottomNavigationView

class ImagesRecyclerViewFragment : Fragment(), ImageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentRecyclerviewBinding
    private lateinit var imageAdapter: ImageAdapter
    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ImagesRecyclerViewFragmentArgs by navArgs()
        val category = args.category
        val subcategory = args.subcategory

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        when (category){
            Helper.ImageType.HEAD.int -> bottomNavigationView?.menu?.findItem(R.id.headFragment)?.isChecked = true
            Helper.ImageType.UPPERBODY.int -> bottomNavigationView?.menu?.findItem(R.id.upperBodyFragment)?.isChecked = true
            Helper.ImageType.LOWERBODY.int -> bottomNavigationView?.menu?.findItem(R.id.lowerBodyFragment)?.isChecked = true
        }

        try{
            if (subcategory != 0) {
                imageSourceViewModel.getImagesByCategoryAndSubcategory(category, subcategory)
                    .observe(viewLifecycleOwner) {
                        imageSourceList ->
                        val adapter = ImageAdapter(imageSourceList, this)
                        binding.recyclerView.adapter = adapter
                        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
                        if (imageSourceList.isEmpty()) {
                            Toast.makeText(
                                requireContext(),
                                "No hay nada para mostrar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                imageSourceViewModel.getImagesByCategory(category)
                    .observe(viewLifecycleOwner) { imageSourceList ->
                        val adapter = ImageAdapter(imageSourceList, this)
                        binding.recyclerView.adapter = adapter
                        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
                        if (imageSourceList.isEmpty()) {
                            Toast.makeText(
                                requireContext(),
                                "No hay nada para mostrar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        } catch (e: Exception){
            Log.e("ImagesRecyclerViewFragment", "Error: ${e.message}")
        }

    }

    override fun onItemClick(idImage: Int) {
        val action = ImagesRecyclerViewFragmentDirections.actionImagesRecyclerViewFragmentToAddToCollectionFragment(idImage)
        findNavController().navigate(action)
    }
}