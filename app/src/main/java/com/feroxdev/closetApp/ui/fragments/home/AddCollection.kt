package com.feroxdev.closetApp.ui.fragments.home

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feroxdev.closetApp.R
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.databinding.FragmentGalleryBinding
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory
import com.feroxdev.closetApp.utilities.Helper
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

class AddCollection : Fragment() {

    private val imageSourceViewModel: ImageSourceViewModel by activityViewModels {
        ImageSourceViewModelFactory((requireActivity().application as App).imageSourceRepository)
    }

    private var binding: FragmentGalleryBinding? = null
    private val onbinding get() = binding!!
    companion object {
        private const val STORAGE_PERMISSION_REQUEST_CODE = 102//CONVECION: CONSTANTES EN MAYUSCULAS
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return onbinding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defineOptions()



        val imageUri = Uri.parse("")
        //muestro la imagen
        onbinding.imageView.adjustViewBounds = true
        onbinding.imageView.setImageURI(imageUri)
        onbinding.button.setOnClickListener{
            val imageSource = createImageSourceModel(imageUri)//genero la imagen a guardar con las opciones elegidas
            try{
                imageSourceViewModel.insert(imageSource)
                Toast.makeText(requireContext(), getString(R.string.scsSavingImg), Toast.LENGTH_SHORT).show()
                //una vez guardado en el repositorio vuelvo atrás
                val navController = findNavController()
                navController.popBackStack()
                findNavController().navigate(R.id.uploadFragment)
            }catch (e: Exception){
                Toast.makeText(requireContext(), getString(R.string.errSavingImg), Toast.LENGTH_SHORT).show()
            }

        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun defineOptions() {
        onbinding.option1RadioButton.text = getString(R.string.hat)
        onbinding.option2RadioButton.text = getString(R.string.glasses)
        onbinding.option3RadioButton.text = getString(R.string.jewelry)
        onbinding.option4RadioButton.text = getString(R.string.others)
    }
    private fun changueOptions(checkedId: Int) {

            when(checkedId){
                onbinding.buttonHead.id -> {
                    onbinding.option1RadioButton.text = getString(R.string.hat)
                    onbinding.option2RadioButton.text = getString(R.string.glasses)
                    onbinding.option3RadioButton.text = getString(R.string.jewelry)
                    onbinding.option4RadioButton.text = getString(R.string.others)
                }
                onbinding.buttonUpper.id ->{
                    onbinding.option1RadioButton.text = getString(R.string.jacket)
                    onbinding.option2RadioButton.text = getString(R.string.tshirt)
                    onbinding.option3RadioButton.text = getString(R.string.sweater)
                    onbinding.option4RadioButton.text = getString(R.string.others)
                }
                onbinding.buttonLower.id -> {
                    onbinding.option1RadioButton.text = getString(R.string.pants)
                    onbinding.option2RadioButton.text = getString(R.string.shorts)
                    onbinding.option3RadioButton.text = getString(R.string.skirt)
                    onbinding.option4RadioButton.text = getString(R.string.bottom)
                }
            }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createImageSourceModel(imageUri: Uri) : ImageSource {
        val name = onbinding.editTextText.text
        var mycategory : Int = -1
        var mysubcategory : Int = -1
        when(onbinding.radioGroup2.checkedRadioButtonId){
            onbinding.buttonHead.id -> mycategory = Helper.ImageType.HEAD.int
            onbinding.buttonLower.id -> mycategory = Helper.ImageType.LOWERBODY.int
            onbinding.buttonUpper.id -> mycategory = Helper.ImageType.UPPERBODY.int
        }
        when (onbinding.optionsRadioGroup.checkedRadioButtonId) {
            onbinding.option1RadioButton.id -> mysubcategory = Helper.Subtype.ONE.int
            onbinding.option2RadioButton.id -> mysubcategory = Helper.Subtype.TWO.int
            onbinding.option3RadioButton.id -> mysubcategory = Helper.Subtype.THREE.int
            onbinding.option4RadioButton.id -> mysubcategory = Helper.Subtype.FOUR.int
        }

        checkStoragePermissions()
        return ImageSource(
            path = copyImageToLocalDirectory(imageUri),
            customName = name.toString(),
            category = mycategory,
            subcategory = mysubcategory,
            dischargeDate = LocalDateTime.now().toString(),
        )
    }
    private fun copyImageToLocalDirectory(imageUri: Uri) : String {
        val inputStream: InputStream? = requireContext().contentResolver.openInputStream(imageUri)
        val outputDir = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "SavedImages")
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_${timeStamp}.jpg"
        val outputFile = File(outputDir, fileName)
        val outputStream: OutputStream = FileOutputStream(outputFile)

        inputStream?.use { input ->
            outputStream.use { output ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                }
                output.flush()
            }
        }

        return outputFile.absolutePath
    }

    private fun checkStoragePermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permisos concedidos, continuar con la operación
            } else {
                // Permisos no concedidos, mostrar mensaje al usuario
                Toast.makeText(requireContext(), getString(R.string.errImagePermission), Toast.LENGTH_SHORT).show()
            }
        }
    }


}