package com.feroxdev.closetApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.feroxdev.closetApp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
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

    /*private val itemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.nav_icon0 -> {
                showCustomDialog()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_icon1 -> selectedFragment = HeadFragment()
            R.id.nav_icon2 -> selectedFragment = UpperbodyFragment()
            R.id.nav_icon3 -> selectedFragment = LowerbodyFragment()
            R.id.nav_icon4 -> selectedFragment = UploadFragment()
        }

        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
        }

        true
    }

    private fun showCustomDialog() {
        val dialogFragment: DialogFragment = InfoFragment()
        dialogFragment.show(supportFragmentManager, "custom_dialog")
    }*/
}