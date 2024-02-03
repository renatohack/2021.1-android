package renatohack.minhascompras.ListasAbertas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.Firebase.ListFirebaseDao
import renatohack.minhascompras.Firebase.TemporaryDao
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.Model.Firestore.ListaFirebase
import renatohack.minhascompras.Model.Room.ListElement
import renatohack.minhascompras.Model.Room.Lista

class ListasAbertasViewModel(val listDao: ListDao) : ViewModel() {

    val firebaseAuth = Firebase.auth
    val uid = firebaseAuth.currentUser!!.uid

    private val _lista = MutableLiveData<MutableList<ListaFirebase>>()
    val lista: LiveData<MutableList<ListaFirebase>>
        get() = _lista

    private val _element = MutableLiveData<MutableList<ElementoTemporario>>()
    val element: LiveData<MutableList<ElementoTemporario>>
        get() = _element

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        ListFirebaseDao().listAll().addSnapshotListener { snapshot, error ->
            if (error != null) {
                _msg.value = error.message
            } else if (snapshot != null && !snapshot.isEmpty) {
                _lista.value = snapshot.toObjects(ListaFirebase::class.java).toMutableList()
            }
        }
    }

        private suspend fun getAllListAndElements(): List<ListElement> =
            listDao.getAllListAndElements()

        suspend fun listElementsToString(): String {
            var stringToPrint = ""

            val lista = getAllListAndElements()

            for (list in lista) {
                var totalList = 0.0f
                stringToPrint += "${list.lista.name}\n\n"

                for (element in list.elementos) {
                    val totalElement = element.price!!.toFloat() * element.quantity!!.toInt()
                    stringToPrint += "Item: ${element.name}\tPreco: ${element.price}\tQtd: ${element.quantity}\tTotal: $totalElement\n"

                    totalList += totalElement
                }

                stringToPrint += "\nTotal da lista: $totalList\n\n" +
                        "------------------------------------------\n\n"
            }

            return stringToPrint
        }

    }