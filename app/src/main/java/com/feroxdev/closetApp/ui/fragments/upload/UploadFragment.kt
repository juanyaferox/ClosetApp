package com.feroxdev.closetApp.ui.fragments.upload

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.databinding.FragmentUploadBinding
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
    ): View {
        binding = FragmentUploadBinding.inflate(inflater, container, false)
        return onbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success) {
                    // Do something with the captured image
                    try{
                        val imageUri = photoUri.toString()
                        val action = UploadFragmentDirections.actionUploadFragmentToGalleryFragment(imageUri)
                        findNavController().navigate(
                            action
                        )
                    } catch (e: Exception) {
                        Log.e("Error", "Error en el arguemento", e)
                    }

                }
            }
        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // Handle the result if the operation was successful
                    try{
                        val imageUri = result.data?.data.toString()
                        val action = UploadFragmentDirections.actionUploadFragmentToGalleryFragment(imageUri)
                        findNavController().navigate(
                            action
                        )
                    }catch (e: Exception) {
                        Log.e("Error", "Error en el arguemento", e)
                    }

                }
            }

        //Botón para abrir la galería
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

    //Función para llamar a la camara y capturar la imagen
    private fun dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            val photoFile = createTempImageFile()
            photoUri = FileProvider.getUriForFile(requireContext(), "com.feroxdev.closetApp.fileprovider", photoFile)
            cameraLauncher.launch(photoUri)
        }
    }

    //Función para crear ubicación temporal para la imagen
    private fun createTempImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }


}