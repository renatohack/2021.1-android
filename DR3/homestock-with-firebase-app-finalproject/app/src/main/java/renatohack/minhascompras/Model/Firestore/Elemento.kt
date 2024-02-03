package renatohack.minhascompras.Model.Firestore

import com.google.firebase.firestore.DocumentId

class Elemento (
    val name: String = "1",
    val price: String = "1",
    val quantity: String = "1",
    val url: String? = "1",
    @DocumentId
    val id: String = name

) {

//    init{
//        val total: Float = price!!.toFloat() * quantity!!.toFloat()
//    }

}