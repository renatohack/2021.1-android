package renatohack.minhascompras.CriarElemento

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import renatohack.minhascompras.Database.Dao.TempDao

class CriarElementoViewModelFactory(val tempDao: TempDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CriarElementoViewModel(tempDao) as T
    }
}