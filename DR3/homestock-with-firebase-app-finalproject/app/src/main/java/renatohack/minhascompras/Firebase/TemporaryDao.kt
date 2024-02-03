package renatohack.minhascompras.Firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.Model.Room.TempElements

object TemporaryDao {


    val firestore = Firebase.firestore
    val uid = Firebase.auth.currentUser!!.uid

    val collection = firestore.collection("$uid-Temp")

    fun insertOrUpdate(temp: ElementoTemporario): Task<Void> {
         return collection.document(temp.id).set(temp)
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

    fun deleteAllTemps() {
        collection.get().addOnSuccessListener {
            it.documents.forEach {
                delete(it.id)
            }
        }
    }

}