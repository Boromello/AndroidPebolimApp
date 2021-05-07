package com.ghostapps.placapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ghostapps.placapp.R
import com.ghostapps.placapp.databinding.ActivityLoginBinding
import com.ghostapps.placapp.viewModel.login.LoginContract
import com.ghostapps.placapp.viewModel.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity: AppCompatActivity(), LoginContract {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })
    }

    override fun navigateTo(newClass: Class<*>) {
        val intent = Intent(this, newClass)
        startActivity(intent)
    }
}