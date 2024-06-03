package com.feroxdev.closetApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.feroxdev.closetApp.data.App
import com.feroxdev.closetApp.databinding.ActivityMainBinding
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

        //setSupportActionBar(binding.toolbar)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //setupActionBarWithNavController(navController)

        val bottomNavigationView = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        //lógica agregada para arregla fallo de navigation
        if (navController.currentDestination?.id != R.id.homeFragment) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                val menu = bottomNavigationView.menu
                for (i in 0 until menu.size()) {
                    val menuItem = menu.getItem(i)
                    menuItem.isChecked = menuItem.itemId == destination.id
                }
            }
        }
        menuInt++
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /*

    private fun showCustomDialog() {
        val dialogFragment: DialogFragment = InfoFragment()
        dialogFragment.show(supportFragmentManager, "custom_dialog")
    }*/
}