package renatohack.minhascompras.Model.Firestore

import com.google.firebase.firestore.DocumentId
import renatohack.minhascompras.Model.Room.TempElements

class ElementoTemporario(
    val name: String = "1",
    val price: String = "1",
    val quantity: String = "1",
    var url: String? = "1",
    @DocumentId
    val id: String = "Temp-${name}"

) {

//    init{
//        val total: Float = price!!.toFloat() * quantity!!.toFloat()
//    }

}