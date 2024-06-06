package com.feroxdev.closetApp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.databinding.FragmentListcollectionBinding
import com.feroxdev.closetApp.ui.adapters.CollectionAdapter
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModelFactory

class ListCollectionFragment : Fragment() {
    private lateinit var binding: FragmentListcollectionBinding

    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }
    private val collectionViewModel: CollectionViewModel by activityViewModels {
        CollectionViewModelFactory((requireActivity().application as App).collectionRepository)
    }
    private val imageSourceCollectionViewModel: ImageSourceCollectionViewModel by activityViewModels {
        ImageSourceCollectionViewModelFactory((requireActivity().application as App).imageSourceCollectionRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListcollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CollectionAdapter { collection ->
            val selectedId = collection.idCollection

            val action = ListCollectionFragmentDirections.actionListCollectionFragmentToImagesRecyclerViewFragment(-1,-1,selectedId)
            findNavController().navigate(
                action
            )
        }
        binding.rvList.adapter = adapter

        // Observar las colecciones y enviarlas al adaptador
        collectionViewModel.getAllCollections().observe(viewLifecycleOwner) { collections ->
            adapter.submitList(collections)
        }
    }
}