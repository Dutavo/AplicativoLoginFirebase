package com.dutavo.aplicativologin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dutavo.aplicativologin.ViewModel.LoginViewModel
import com.dutavo.aplicativologin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.buttonEntrarHome.setOnClickListener{
            val name = binding.editTextNome.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextSenha.text.toString()

            if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.register(name, email, password)
            }
        }

        binding.imageViewFechar.setOnClickListener{
            finish()
        }

        loginViewModel.loginStatus.observe(this, Observer { status ->
           Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })

        loginViewModel.navigateToHome.observe(this, Observer { navigate ->
            if (navigate) {
                startActivity(Intent(this, DashboardActivity :: class.java))
                loginViewModel.OnNavigateToHomeComplete()
            }
        })
    }

    fun navigateToSignIn(view : View){
        startActivity(Intent(this, SignInActivity :: class.java))
    }
}
