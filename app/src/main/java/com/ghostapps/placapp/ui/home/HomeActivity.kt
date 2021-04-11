package com.ghostapps.placapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.ghostapps.placapp.R
import com.ghostapps.placapp.databinding.ActivityHomeBinding
import com.ghostapps.placapp.viewModel.home.HomeContract
import com.ghostapps.placapp.viewModel.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), HomeContract {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
    }

    override fun navigateTo(newClass: Class<*>) {
        val intent = Intent(this, newClass)
        startActivity(intent)
    }

    override fun closeApp() {
        finish()
    }

}