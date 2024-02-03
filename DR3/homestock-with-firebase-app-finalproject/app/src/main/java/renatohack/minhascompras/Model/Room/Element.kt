package renatohack.minhascompras.Model.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Element(
    val name: String?,
    val price: String?,
    val quantity: String?,

    val listID: Long,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0) {

    init{
        val total: Float = price!!.toFloat() * quantity!!.toFloat()
    }

}