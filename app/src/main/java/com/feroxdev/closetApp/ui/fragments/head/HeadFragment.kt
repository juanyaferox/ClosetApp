package com.feroxdev.closetApp.ui.fragments.head

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.databinding.FragmentCommonsubtypesBinding
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory
import com.feroxdev.closetApp.utilities.Helper

/**
 * A simple [Fragment] subclass.
 * Use the [HeadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeadFragment : Fragment() {

    private lateinit var binding: FragmentCommonsubtypesBinding

    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommonsubtypesBinding.inflate(inflater, container, false)
        binding.textView2.text = getString(R.string.title_head)
        binding.button1.text = getString(R.string.hat)
        binding.button2.text = getString(R.string.glasses)
        binding.button3.text = getString(R.string.jewelry)
        binding.button4.text = getString(R.string.others)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.HeadSubtype.BONNET.int)
        }

        binding.button2.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.HeadSubtype.GLASSES.int)
        }

        binding.button3.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.HeadSubtype.JEWELRY.int)
        }

        binding.button4.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.HeadSubtype.OTHERS.int)
        }

    binding.button5.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(0)
        }
    }

    //Función para navegar a la RecyclerView de imágenes
    private fun navigateToImagesRecyclerView(subcategory: Int) {
        val action = HeadFragmentDirections.actionHeadFragmentToImagesRecyclerViewFragment(
            Helper.ImageType.HEAD.int, subcategory, -1)
        findNavController().navigate(action)
    }
}