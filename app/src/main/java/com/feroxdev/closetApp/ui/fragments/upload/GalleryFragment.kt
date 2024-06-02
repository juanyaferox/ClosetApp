package com.feroxdev.closetApp.ui.fragments.upload

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.feroxdev.closetApp.databinding.FragmentGalleryBinding
import com.feroxdev.closetApp.databinding.FragmentUploadBinding

class GalleryFragment : Fragment() {

    private var binding: FragmentGalleryBinding? = null
    private val onbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return onbinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUriString = arguments?.getString("imageUri")
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            onbinding.imageView.setImageURI(imageUri)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}