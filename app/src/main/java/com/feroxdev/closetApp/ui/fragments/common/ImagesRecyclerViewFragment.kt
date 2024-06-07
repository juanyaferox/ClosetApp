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
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModelFactory
import com.feroxdev.closetApp.utilities.Helper
import com.google.android.material.bottomnavigation.BottomNavigationView

class ImagesRecyclerViewFragment : Fragment(), ImageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentRecyclerviewBinding

    // Inicialización de los ViewModels
    private lateinit var imageAdapter: ImageAdapter
    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }
    private val imageSourceCollectionViewModel: ImageSourceCollectionViewModel by activityViewModels {
        ImageSourceCollectionViewModelFactory((requireActivity().application as App).imageSourceCollectionRepository)
    }
    private val collectionViewModel: CollectionViewModel by activityViewModels {
        CollectionViewModelFactory((requireActivity().application as App).collectionRepository)
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
        val collection = args.imageCollection


        // Configurar la barra de navegación inferior
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        when (category){
            Helper.ImageType.HEAD.int -> bottomNavigationView?.menu?.findItem(R.id.headFragment)?.isChecked = true
            Helper.ImageType.UPPERBODY.int -> bottomNavigationView?.menu?.findItem(R.id.upperBodyFragment)?.isChecked = true
            Helper.ImageType.LOWERBODY.int -> bottomNavigationView?.menu?.findItem(R.id.lowerBodyFragment)?.isChecked = true
        }
        if (collection > 0)
            bottomNavigationView?.menu?.findItem(R.id.homeFragment)?.isChecked = true

        try{
            //Muestra las imagenes realacionadas a una categoria y subcategoria
            if (subcategory > 0) {
                imageSourceViewModel.getImagesByCategoryAndSubcategory(category, subcategory)
                    .observe(viewLifecycleOwner) {
                        imageSourceList ->
                        binding.textView2.text = getString(Helper.categoryAndSubcategoryToString(category,subcategory))
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

            //Muestra todas las imagenes relacionadas a una colección
            else if (collection > 0) {
                collectionViewModel.getCollectionById(collection).observe(viewLifecycleOwner){
                    binding.textView2.text = it?.collectionName
                }
                imageSourceCollectionViewModel.getImagesForCollection(collection)
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
            }

            //Muestra todas las imagenes relacionadas a una categoria
            else {
                imageSourceViewModel.getImagesByCategory(category)
                    .observe(viewLifecycleOwner) {
                        imageSourceList ->
                        binding.textView2.text = getString(Helper.categoryToString(category))
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

    //Permite que al click en una imagen podamos añadirla a una colección previamente creada
    override fun onItemClick(idImage: Int) {
        val args: ImagesRecyclerViewFragmentArgs by navArgs()
        val subcategory = args.subcategory
        val action = ImagesRecyclerViewFragmentDirections.actionImagesRecyclerViewFragmentToAddToCollectionFragment(idImage, subcategory)
        findNavController().navigate(action)
    }
}