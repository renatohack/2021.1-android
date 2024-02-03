package renatohack.minhascompras.ListasAbertas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import renatohack.minhascompras.Database.Dao.ListDao

class ListasAbertasViewModelFactory (val listDao: ListDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListasAbertasViewModel(listDao) as T
    }
}