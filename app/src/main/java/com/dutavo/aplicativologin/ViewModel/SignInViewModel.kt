package com.dutavo.aplicativologin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel : ViewModel(){

    private val _signInStatus = MutableLiveData<String>()
    val signInStatus : LiveData<String> get() = _signInStatus

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome : LiveData<Boolean> get() = _navigateToHome

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email : String, password : String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
               if(task.isSuccessful){
                   _signInStatus.value = "Login Bem-Sucessido"
                   _navigateToHome.value = true
               } else {
                   _signInStatus.value = "Falha no Login: ${task.exception?.message}"
               }

            }
    }

    fun OnNavigateToHomeComplete() {
        _navigateToHome.value = false
    }
}