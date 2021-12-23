package com.example.pharmacyapp.ui.fragment.prepare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pharmacyapp.MainActivity
import com.example.pharmacyapp.R
import com.example.pharmacyapp.databinding.ActivityPrepareBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrepareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrepareBinding
    private val viewModel: PrepareViewModel by lazy {
        ViewModelProvider(
            this,
            PrepareViewModelFactory(requireNotNull(this).application)
        )[PrepareViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_prepare)
    }

    fun navigateToAnotherActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this@PrepareActivity.finish()
    }
}