package com.renatohack.renato_hack_dr4_at.Login.Entrar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    val firebaseAuth = Firebase.auth

    init {
        _status.value = false

        if (firebaseAuth.currentUser != null){
            _status.value = true
        }
    }

    fun login(email: String, senha: String){


        if(!email.isNullOrBlank() && !senha.isNullOrBlank()) {

            val task = firebaseAuth.signInWithEmailAndPassword(email, senha)

            task
                .addOnSuccessListener {
                    if (it != null && it.user != null) {
                        _msg.value = "Olá ${it.user!!.email}"
                        _status.value = true
                    }
                }
                .addOnFailureListener {
                    _msg.value = it.message
                }
        }
        else{
            _msg.value = "Preencher ambos os campos!"
        }
    }


}