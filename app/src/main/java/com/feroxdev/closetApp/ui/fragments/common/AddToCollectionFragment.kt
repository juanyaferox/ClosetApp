package com.feroxdev.closetApp.ui.fragments.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.data.model.Collection
import com.feroxdev.closetApp.data.model.ImageSourceCollectionCrossRef
import com.feroxdev.closetApp.databinding.FragmentAddtocollectionBinding
import com.feroxdev.closetApp.ui.adapters.CollectionAdapter
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModelFactory
import com.feroxdev.closetApp.utilities.Helper
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddToCollectionFragment : Fragment() {
    private lateinit var binding: FragmentAddtocollectionBinding

    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }
    private val collectionViewModel: CollectionViewModel by activityViewModels {
        CollectionViewModelFactory((requireActivity().application as App).collectionRepository)
    }
    private val imageSourceCollectionViewModel: ImageSourceCollectionViewModel by activityViewModels {
        ImageSourceCollectionViewModelFactory((requireActivity().application as App).imageSourceCollectionRepository)
    }

    // Variable para almacenar la colección seleccionada
    private var selectedCollection: Collection? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddtocollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddToCollectionFragmentArgs by navArgs()
        val idImage = args.idImage

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        //Selecciona el submenu correspondiente
        imageSourceViewModel.getImageById(idImage).observe(viewLifecycleOwner) { image ->
            binding.imageView2.setImageURI(image?.path?.toUri())
            when (image?.category){
                Helper.ImageType.HEAD.int -> bottomNavigationView?.menu?.findItem(R.id.headFragment)?.isChecked = true
                Helper.ImageType.UPPERBODY.int -> bottomNavigationView?.menu?.findItem(R.id.upperBodyFragment)?.isChecked = true
                Helper.ImageType.LOWERBODY.int -> bottomNavigationView?.menu?.findItem(R.id.lowerBodyFragment)?.isChecked = true
            }
        }

        // Configurar el adaptador del RecyclerView
        val adapter = CollectionAdapter { collection ->
            selectedCollection = collection
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Observar las colecciones y actualizar el adaptador cuando los datos cambien
        collectionViewModel.getAllCollections().observe(viewLifecycleOwner) { collections ->
            adapter.submitList(collections)
        }

        binding.button6.setOnClickListener {
            handleObjectSelection(idImage)
        }
    }

    // Método para manejar la lógica de guardar la imagen en la colección seleccionada
    private fun handleObjectSelection(idImage:Int) {
        if (selectedCollection!= null) {
            try{
                selectedCollection?.let {
                    collection ->
                    val selectedId = collection.idCollection
                    imageSourceCollectionViewModel.insert(
                        ImageSourceCollectionCrossRef(
                        idImageSource = idImage,
                        idCollection = selectedId
                        )
                    )
                    Toast.makeText(requireContext(), getString(R.string.scsSaving), Toast.LENGTH_SHORT).show()

                    // Navegar a otro fragmento después de guardar
                    val args: AddToCollectionFragmentArgs by navArgs()
                    imageSourceViewModel.getImageById(idImage).observe(viewLifecycleOwner) { image ->
                        val action = AddToCollectionFragmentDirections.actionAddToCollectionFragmentToImagesRecyclerViewFragment2(
                            image?.category ?: 0, args.subcategory, -1
                        )
                        findNavController().navigate(
                            action
                        )
                    }
                }

            } catch (e: Exception){
            Toast.makeText(requireContext(), getString(R.string.errSaving), Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(requireContext(), getString(R.string.errSelect), Toast.LENGTH_SHORT).show()
        }
    }
}
