package com.feroxdev.closetApp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.data.model.Collection
import com.feroxdev.closetApp.databinding.FragmentAddcollectionBinding
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModelFactory

class AddCollection : Fragment() {

    private val collectionViewModel: CollectionViewModel by activityViewModels {
        CollectionViewModelFactory((requireActivity().application as App).collectionRepository)
    }

    private lateinit var binding: FragmentAddcollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddcollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recibe el texto escrito y lo guarda en la bbdd
        binding.button7.setOnClickListener {
            val text = binding.editTextText2.text.toString()
            if (text.isNotEmpty()) {
                try{
                    collectionViewModel.insert(
                        Collection(
                            collectionName = text
                        )
                    )
                    Toast.makeText(requireContext(), getString(R.string.scsSavingImg), Toast.LENGTH_SHORT).show()
                    //una vez guardado en el repositorio vuelvo atrás
                    val navController = findNavController()
                    navController.popBackStack()
                    findNavController().navigate(R.id.homeFragment)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), getString(R.string.errSaving), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.errSaving), Toast.LENGTH_SHORT).show()
            }
        }
    }
}