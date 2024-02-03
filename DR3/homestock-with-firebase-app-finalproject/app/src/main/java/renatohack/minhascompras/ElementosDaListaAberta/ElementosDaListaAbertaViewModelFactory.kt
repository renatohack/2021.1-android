package renatohack.minhascompras.ElementosDaListaAberta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import renatohack.minhascompras.Database.Dao.ElementDao
import renatohack.minhascompras.Database.Dao.ListDao

class ElementosDaListaAbertaViewModelFactory(private val listDao: ListDao,
                                             private val id : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ElementosDaListaAbertaViewModel(listDao, id) as T
    }
}