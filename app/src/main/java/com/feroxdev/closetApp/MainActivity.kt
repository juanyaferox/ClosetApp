package com.feroxdev.closetApp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.feroxdev.closetApp.ui.fragments.HeadFragment
import com.feroxdev.closetApp.ui.fragments.HomeFragment
import com.feroxdev.closetApp.ui.fragments.LowerbodyFragment
import com.feroxdev.closetApp.ui.fragments.UploadFragment
import com.feroxdev.closetApp.ui.fragments.UpperbodyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_icon0 -> selectedFragment = HomeFragment()
                R.id.nav_icon1 -> selectedFragment = HeadFragment()
                R.id.nav_icon2 -> selectedFragment = UpperbodyFragment()
                R.id.nav_icon3 -> selectedFragment = LowerbodyFragment()
                R.id.nav_icon4 -> selectedFragment = UploadFragment()
            }//NO PETA

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,selectedFragment).commit()
            }

            true
        }
    }
}
