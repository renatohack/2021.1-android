package renatohack.minhascompras.NovaLista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import renatohack.minhascompras.Database.Dao.ElementDao
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.Database.Dao.TempDao
import renatohack.minhascompras.Firebase.ListFirebaseDao
import renatohack.minhascompras.Firebase.TemporaryDao
import renatohack.minhascompras.Model.Firestore.Elemento
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.Model.Firestore.ListaFirebase
import renatohack.minhascompras.Model.Room.Element
import renatohack.minhascompras.Model.Room.Lista

class FazerNovaListaViewModel(val tempDao : TempDao,
                              val listDao : ListDao,
                              val elementDao : ElementDao) : ViewModel() {

    val firebaseAuth = Firebase.auth
    val uid = firebaseAuth.currentUser!!.uid


    private val _element = MutableLiveData<MutableList<ElementoTemporario>>()
    val element: LiveData<MutableList<ElementoTemporario>>
        get() = _element

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        TemporaryDao.listAll().addSnapshotListener{snapshot, error ->
            if (error != null){
                _msg.value = error.message
            } else if (snapshot != null && !snapshot.isEmpty){
                _element.value = snapshot.toObjects(ElementoTemporario::class.java).toMutableList()
            }
        }

    }


//    fun updateList() {
//        TemporaryDao.listAll().addSnapshotListener{snapshot, error ->
//            if (error != null){
//                _msg.value = error.message
//            } else if (snapshot != null && !snapshot.isEmpty){
//                _element.value = snapshot.toObjects(ElementoTemporario::class.java).toMutableList()
//            }
//        }
//    }

    fun insertList(lista: ListaFirebase) {
            ListFirebaseDao().insert(lista)
    }

    suspend fun getLastList(): Lista? {
        return listDao.getLastList()
    }

    suspend fun insertElement(element: Element) {
            elementDao.insert(element)
    }

    fun deleteAllTemps() {
        TemporaryDao.deleteAllTemps()
    }

    fun tempToElements(): MutableList<Elemento> {
        val elementos : MutableList<Elemento> = mutableListOf()

        for(i in 0.._element.value!!.lastIndex){
            val name = _element.value!![i].name
            val price = _element.value!![i].price
            val quantity = _element.value!![i].quantity
            val url = _element.value!![i].url

            elementos.add(Elemento(name, price, quantity, url))
        }

        return elementos
    }

    suspend fun updateElementsInList(){

        val novaLista = getLastList()

        for (elemento in _element.value!!) {

            val name = elemento.name
            val price = elemento.price
            val quantity = elemento.quantity
            val listID = novaLista?.id

            if (listID != null) {
                val element = Element(name, price, quantity, listID)
                insertElement(element)
            }
        }

        deleteAllTemps()
//        updateList()
    }

}