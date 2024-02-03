package renatohack.minhascompras.CriarElemento

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import renatohack.minhascompras.Database.Dao.TempDao
import renatohack.minhascompras.Firebase.TemporaryDao
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.Model.GoogleAPI.ApiClient
import java.lang.Exception

class CriarElementoViewModel(val tempDao: TempDao) : ViewModel() {

    val firebaseAuth = Firebase.auth
    val uid = firebaseAuth.currentUser!!.uid

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun insertTemporaryDao(temp: ElementoTemporario){
        viewModelScope.launch{
            temp.url = ApiClient.getGoogleAPIService().pesquisar(temp.name).items[0].link

            TemporaryDao.insertOrUpdate(temp)
                .addOnSuccessListener {
                    _msg.value = "Item: ${temp.name} adicionado com sucesso."
                    _status.value = true
                }
                .addOnFailureListener {
                    _msg.value = it.message
                }
        }
    }
}