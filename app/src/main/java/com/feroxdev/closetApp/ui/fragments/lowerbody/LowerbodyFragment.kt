package com.feroxdev.closetApp.ui.fragments.lowerbody

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

class LowerbodyFragment : Fragment() {

    private lateinit var binding: FragmentCommonsubtypesBinding

    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCommonsubtypesBinding.inflate(inflater, container, false)
        binding.textView2.text = getString(R.string.title_lowerbody)
        binding.button1.text = getString(R.string.pants)
        binding.button2.text = getString(R.string.shorts)
        binding.button3.text = getString(R.string.skirt)
        binding.button4.text = getString(R.string.bottom)
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
            navigateToImagesRecyclerView(Helper.LowerSubtype.PANTS.int)
        }
        binding.button2.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.LowerSubtype.SHORTS.int)
        }
        binding.button3.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.LowerSubtype.SKIRT.int)
        }
        binding.button4.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.LowerSubtype.BOTTOM.int)
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
    private fun navigateToImagesRecyclerView(subcategory: Int) {
        val action = LowerbodyFragmentDirections.actionLowerBodyFragmentToImagesRecyclerViewFragment(
            Helper.ImageType.LOWERBODY.int, subcategory, -1)
        findNavController().navigate(action)
    }

}