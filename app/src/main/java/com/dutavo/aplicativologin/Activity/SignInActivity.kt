package com.dutavo.aplicativologin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dutavo.aplicativologin.ViewModel.SignInViewModel
import com.dutavo.aplicativologin.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEntrarHome.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextSenha.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                signInViewModel.signIn(email, password)
            }
        }

        binding.imageViewFechar.setOnClickListener {
            finish()
        }

        signInViewModel.signInStatus.observe(this, Observer { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })

        signInViewModel.navigateToHome.observe(this, Observer { navigate ->
            if (navigate) {
                startActivity(Intent(this, DashboardActivity::class.java))
                signInViewModel.OnNavigateToHomeComplete()
            }
        })

        fun navigateToLogin(view: View) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
