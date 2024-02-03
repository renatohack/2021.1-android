package renatohack.minhascompras.Firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.Model.Firestore.ListaFirebase

class ListFirebaseDao {

    val firestore = Firebase.firestore
    val uid = Firebase.auth.currentUser!!.uid

    val collection = firestore.collection("$uid-Listas")

    fun insert(lista: ListaFirebase): Task<Void> {
        return collection.document(lista.id).set(lista)
    }

    fun delete(documentId: String): Task<Void> {
        return collection
            .document(documentId)
            .delete()
    }

    fun show(documentId: String): Task<DocumentSnapshot> {
        return collection
            .document(documentId)
            //.addSnapshotListener { value, error ->  }
            .get()
    }

    //Atualizar
    fun update(documentId: String, temp: ElementoTemporario): Task<Void> {
        return collection
            .document(documentId)
            .set(temp)
    }

    fun listAll(): Query {
        return collection
            // Indica por qual atributo o resultado sera ordenado
            .orderBy("name", /*Query.Direction.DESCENDING*/)
    }
}