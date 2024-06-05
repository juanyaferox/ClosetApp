package com.feroxdev.closetApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.databinding.ActivityMainBinding
import com.feroxdev.closetApp.ui.fragments.misc.InfoFragment
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.Collection.CollectionViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSource.ImageSourceViewModelFactory
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection.ImageSourceCollectionViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private val imageSourceViewModel: ImageSourceViewModel by viewModels {
        ImageSourceViewModelFactory((application as App).imageSourceRepository)
    }

    private val collectionViewModel: CollectionViewModel by viewModels {
        CollectionViewModelFactory((application as App).collectionRepository)
    }

    private val imageSourceCollectionViewModel: ImageSourceCollectionViewModel by viewModels {
        ImageSourceCollectionViewModelFactory((application as App).imageSourceCollectionRepository)
    }
    private var menuInt : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageButton.setOnClickListener { showCustomDialog() }
        //setSupportActionBar(binding.toolbar)



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //setupActionBarWithNavController(navController)

        val bottomNavigationView = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.galleryFragment -> bottomNavigationView.menu.findItem(R.id.uploadFragment).isChecked = true
            }
            if (destination.id == R.id.imagesRecyclerViewFragment) {
                // Establece el elemento del menú correspondiente como seleccionado
                bottomNavigationView.menu.findItem(R.id.uploadFragment).isChecked = true
            }
            if (destination.id != R.id.homeFragment && destination.id != R.id.uploadFragment && destination.id != R.id.lowerBodyFragment
                && destination.id != R.id.upperBodyFragment && destination.id != R.id.headFragment) {
                binding.textView3.text = getString(R.string.app_name)
            }
            else {
                binding.textView3.text = ""
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun showCustomDialog() {
        val dialogFragment: DialogFragment = InfoFragment()
        dialogFragment.show(supportFragmentManager, "custom_dialog")
    }
}