package renatohack.minhascompras.NovaLista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import renatohack.minhascompras.Database.Dao.ElementDao
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.Database.Dao.TempDao

class FazerNovaListaViewModelFactory(val tempDao : TempDao,
                                     val listDao : ListDao,
                                     val elementDao : ElementDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FazerNovaListaViewModel(tempDao, listDao, elementDao) as T
    }
}