package com.example.pharmacyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pharmacyapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding initialize
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //bottom navigation setup
        val navView: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.myNavHostFragment)
        navView.setupWithNavController(navController)
    }
}