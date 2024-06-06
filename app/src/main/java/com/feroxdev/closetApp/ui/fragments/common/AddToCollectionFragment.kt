package com.feroxdev.closetApp.ui.fragments.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.data.model.Collection
import com.feroxdev.closetApp.databinding.FragmentAddtocollectionBinding
import com.feroxdev.closetApp.ui.adapters.CollectionAdapter
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory

class AddToCollectionFragment : Fragment() {
    private lateinit var binding: FragmentAddtocollectionBinding

    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }
    private val collectionViewModel: CollectionViewModel by activityViewModels {
        CollectionViewModelFactory((requireActivity().application as App).collectionRepository)
    }

    private var selectedCollection: Collection? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddtocollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddToCollectionFragmentArgs by navArgs()
        val idImage = args.idImage

        imageSourceViewModel.getImageById(idImage).observe(viewLifecycleOwner) { image ->
            binding.imageView2.setImageURI(image?.path?.toUri())
        }

        val adapter = CollectionAdapter { collection ->
            selectedCollection = collection
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        collectionViewModel.getAllCollections().observe(viewLifecycleOwner) { collections ->
            adapter.submitList(collections)
        }

        binding.button6.setOnClickListener {
            handleObjectSelection()
        }
    }

    private fun handleObjectSelection() {
        selectedCollection?.let { collection ->
            val selectedObjectId = collection.idCollection
            Toast.makeText(requireContext(), "ID del objeto seleccionado: $selectedObjectId", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(requireContext(), "Por favor, selecciona una colección.", Toast.LENGTH_SHORT).show()
    }
}
