package com.feroxdev.closetApp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.feroxdev.closetApp.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Cargo el idioma guardado
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        language?.let { setLocale(it) }

        //Inflamos la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Cargamos el navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        //Configuramos el boton de navegación
        val bottomNavigationView = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)



        //Lógica creada con 2 objetivos:
        // - Corregir un bug que no mostraba correctamente seleccionado el icono del submenu al cambiar a otro y volver
        // - Ocultar texto de nombre de la app en otra pantallas q no sean las principales
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.galleryFragment -> bottomNavigationView.menu.findItem(R.id.uploadFragment).isChecked = true
                R.id.listCollectionFragment -> bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
                R.id.addCollection -> bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
            }
            if (destination.id != R.id.homeFragment && destination.id != R.id.uploadFragment && destination.id != R.id.lowerBodyFragment
                && destination.id != R.id.upperBodyFragment && destination.id != R.id.headFragment) {
                binding.textView3.text = ""
            }
            else {
                binding.textView3.text = getString(R.string.app_name)
            }
        }
    }

    //Función para cargar el idioma guardado
    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}