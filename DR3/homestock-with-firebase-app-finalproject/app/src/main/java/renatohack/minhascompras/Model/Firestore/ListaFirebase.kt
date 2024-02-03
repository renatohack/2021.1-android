package renatohack.minhascompras.Model.Firestore

import com.google.firebase.firestore.DocumentId

class ListaFirebase(
    val name: String = "1",
    val elementos: MutableList<Elemento> = mutableListOf(Elemento("-", "-", "-", "-")),
    @DocumentId
    val id: String = "Lista-${name}"
) {
}