package com.renatohack.renato_hack_dr4_at.CadastroAnotacao

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renatohack.renato_hack_dr4_at.MainActivity

class CadastroAnotacaoViewModelFactory(val context: Context, val activity: MainActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CadastroAnotacaoViewModel(
            context,
            activity
        ) as T
    }
}