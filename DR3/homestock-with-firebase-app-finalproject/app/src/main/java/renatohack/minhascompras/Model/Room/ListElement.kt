package renatohack.minhascompras.Model.Room

import androidx.room.Embedded
import androidx.room.Relation
import renatohack.minhascompras.Model.Room.Element
import renatohack.minhascompras.Model.Room.Lista

class ListElement(

    @Embedded
    val lista : Lista,

    @Relation(parentColumn = "id", entityColumn = "listID")
    val elementos : MutableList<Element>

) {
}