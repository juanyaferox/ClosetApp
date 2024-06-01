package com.feroxdev.closetApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.feroxdev.closetApp.data.dao.ImageSourceDAO
import com.feroxdev.closetApp.data.database.ImageSourceDatabase
import com.feroxdev.closetApp.data.instance.ImageSourceApplication
import com.feroxdev.closetApp.databinding.ActivityMainBinding
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceViewModel
import com.feroxdev.closetApp.ui.viewmodels.ImageSourceViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val imageSourceViewModel: ImageSourceViewModel by viewModels{
        ImageSourceViewModelFactory((application as ImageSourceApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
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