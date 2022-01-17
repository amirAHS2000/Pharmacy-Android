package com.example.pharmacyapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pharmacyapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PharmacyApp)
        //binding initialize
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //bottom navigation setup
        val navView: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.myNavHostFragment)
        navView.setupWithNavController(navController)

        //for hiding bottom navigation in other fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNavigation()
                R.id.storeFragment -> showBottomNavigation()
                R.id.profileFragment -> showBottomNavigation()
                else -> hideBottomNavigation()
            }
        }
    }

    private fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }
}