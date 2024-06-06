package com.feroxdev.closetApp.ui.fragments.upperbody

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.databinding.FragmentCommonsubtypesBinding
import com.feroxdev.closetApp.utilities.Helper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpperbodyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpperbodyFragment : Fragment() {

    private lateinit var binding: FragmentCommonsubtypesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCommonsubtypesBinding.inflate(inflater, container, false)
        binding.textView2.text = getString(R.string.title_upperbody)
        binding.button1.text = getString(R.string.jacket)
        binding.button2.text = getString(R.string.tshirt)
        binding.button3.text = getString(R.string.sweater)
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
            navigateToImagesRecyclerView(Helper.UpperSubtype.JACKET.int)
        }
        binding.button2.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.UpperSubtype.SHIRT.int)
        }
        binding.button3.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.UpperSubtype.SWEATER.int)
        }
        binding.button4.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            navigateToImagesRecyclerView(Helper.UpperSubtype.OTHERS.int)
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
        val action = UpperbodyFragmentDirections.actionUpperBodyFragmentToImagesRecyclerViewFragment(
            Helper.ImageType.UPPERBODY.int, subcategory, -1)
        findNavController().navigate(action)
    }


}