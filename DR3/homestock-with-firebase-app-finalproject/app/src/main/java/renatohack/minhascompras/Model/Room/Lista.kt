package renatohack.minhascompras.Model.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Lista (
    val name : String,

    @PrimaryKey(autoGenerate = true)
        val id : Long = 0) {
}