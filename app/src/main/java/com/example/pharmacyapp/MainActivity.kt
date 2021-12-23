package com.example.pharmacyapp

import android.os.Bundle
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

        //binding initialize
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //bottom navigation setup
        val navView: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.myNavHostFragment)
        navView.setupWithNavController(navController)
    }
}