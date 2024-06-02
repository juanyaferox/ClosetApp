package com.feroxdev.closetApp.ui.fragments.upload

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.databinding.FragmentUploadBinding
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class UploadFragment : Fragment() {

    private var binding: FragmentUploadBinding? = null
    private val onbinding get() = binding!!

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    private lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    private lateinit var photoUri: Uri

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUploadBinding.inflate(inflater, container, false)
        return onbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success) {
                    // Do something with the captured image
                    val bundle = Bundle().apply {
                        putString("imageUri", photoUri.toString())
                    }
                    findNavController().navigate(
                        R.id.action_uploadFragment_to_galleryFragment,
                        bundle
                    )
                }
            }
        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // Handle the result if the operation was successful
                    val imageUri = result.data?.data.toString()
                    // Do something with the selected image URI, like display it in an ImageView
                    val bundle = Bundle().apply {
                        putString("imageUri", imageUri)
                    }
                    findNavController().navigate(
                        R.id.action_uploadFragment_to_galleryFragment,
                        bundle
                    )
                }
            }


        onbinding.galleryButtom.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            // Start the activity with the launcher
            galleryLauncher.launch(intent)

        }
        onbinding.takePhotoButtom.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.click_animation
                )
            )
            dispatchTakePictureIntent()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            val photoFile = createTempImageFile()
            photoUri = FileProvider.getUriForFile(requireContext(), "com.feroxdev.closetApp.fileprovider", photoFile)
            cameraLauncher.launch(photoUri)
        }
    }

    private fun createTempImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }


}