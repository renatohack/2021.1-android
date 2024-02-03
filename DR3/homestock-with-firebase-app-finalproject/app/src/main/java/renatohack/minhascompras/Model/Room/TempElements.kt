package renatohack.minhascompras.Model.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TempElements(
    val name: String,
    val price: String,
    val quantity: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0)
      {

}