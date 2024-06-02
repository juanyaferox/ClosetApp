package com.feroxdev.closetApp.ui.fragments.lowerbody

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.feroxdev.closetApp.databinding.FragmentLowerbodyBinding

class LowerbodyFragment : Fragment() {

    private var binding: FragmentLowerbodyBinding? = null
    private val unbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLowerbodyBinding.inflate(inflater, container, false)
        return unbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Ahora puedes usar el binding para acceder a las vistas

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}