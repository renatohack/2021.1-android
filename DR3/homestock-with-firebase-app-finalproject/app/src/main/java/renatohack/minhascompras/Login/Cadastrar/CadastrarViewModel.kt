package renatohack.minhascompras.Login.Cadastrar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastrarViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        _status.value = false
    }


    val firebaseAuth = Firebase.auth

    fun createUser(email: String, senha: String, repetirSenha: String) {

        if(!email.isNullOrBlank() && !senha.isNullOrBlank()) {

            if(senha == repetirSenha){

                val task = firebaseAuth.createUserWithEmailAndPassword(email, senha)

                task
                    .addOnSuccessListener {
                        _msg.value = "${it.user!!.email} cadastrado com sucesso!\nOlá ${it.user!!.email}"
                        _status.value = true
                    }
                    .addOnFailureListener {
                        _msg.value = it.message
                    }
            }
            else {
                _msg.value = "A senha precisa ser confirmada corretamente."
            }
        }
        else {
            _msg.value = "Preencher todos os campos!"
        }
    }



}