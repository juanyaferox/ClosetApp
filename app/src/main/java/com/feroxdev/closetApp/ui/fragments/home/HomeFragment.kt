package com.feroxdev.closetApp.ui.fragments.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.databinding.FragmentHomeBinding
import com.feroxdev.closetApp.ui.fragments.misc.InfoFragment
import com.feroxdev.closetApp.utilities.PreferenceUtils
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.infoButtom.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            showCustomDialog() }
        binding.l19nButtom.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            val currentLanguage = PreferenceUtils.getLanguage(requireContext())
            val newLanguage = if (currentLanguage == "en") "es" else "en"
            PreferenceUtils.setLanguage(requireContext(), newLanguage)
            setLocale(newLanguage)
            refreshFragment()
            Toast.makeText(requireContext(), getString(R.string.txtI18nChanged), Toast.LENGTH_SHORT).show()
        }
        binding.addCollectionButtom.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            val action = HomeFragmentDirections.actionHomeFragmentToAddCollection()
            findNavController().navigate(
                action
            )
        }
        binding.listCollectionButtom.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            val action = HomeFragmentDirections.actionHomeFragmentToListCollectionFragment()
            findNavController().navigate(
                action
            )
        }
    }

    private fun showCustomDialog() {
        val dialogFragment: DialogFragment = InfoFragment()
        dialogFragment.show(parentFragmentManager,"custom_dialog")
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        activity?.apply {
            resources.updateConfiguration(config, resources.displayMetrics)
            recreate()  // Restart activity to apply changes
        }
    }

    private fun refreshFragment() {
        val navController = findNavController()
        navController.popBackStack()
        navController.navigate(R.id.homeFragment)
    }
}