package renatohack.minhascompras.Firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import renatohack.minhascompras.Model.Firestore.Elemento
import renatohack.minhascompras.Model.Room.Element

object ElementFirebaseDao {

    val firestore = Firebase.firestore
    val uid = Firebase.auth.currentUser!!.uid

    val collection = firestore.collection("$uid-Listas")

    fun getElementos(documentId: String): Task<DocumentSnapshot> {
        val docRef =  collection.document(documentId)
//        return docRef.get()
        return ListFirebaseDao().show(documentId)
    }

    fun delete(element: Elemento, listID: String): Task<Void> {
        return ListFirebaseDao().collection
            .document(listID).update("elementos", FieldValue.arrayRemove(element))
    }


}