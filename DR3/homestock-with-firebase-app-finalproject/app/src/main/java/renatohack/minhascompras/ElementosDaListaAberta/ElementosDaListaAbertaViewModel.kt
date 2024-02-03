package renatohack.minhascompras.ElementosDaListaAberta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.Firebase.ElementFirebaseDao
import renatohack.minhascompras.Model.Firestore.Elemento
import renatohack.minhascompras.Model.Firestore.ListaFirebase
import renatohack.minhascompras.Model.Room.Element
import renatohack.minhascompras.Model.Room.Lista

class ElementosDaListaAbertaViewModel(private val listDao: ListDao, private val listID : String) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _element = MutableLiveData<MutableList<Elemento>>()
    val element: LiveData<MutableList<Elemento>>
        get() = _element

    init {
        ElementFirebaseDao.getElementos(listID).addOnSuccessListener {
                val lista = it.toObject(ListaFirebase::class.java)
            if (lista != null) {
                _element.value = lista.elementos
                _name.value = lista.name
            }
        }
    }

//    suspend fun getListAndElementsByListId(id : String): ListElement {
//        val list = listDao.getListAndElementsByListId(id)
//        return list
//    }

}