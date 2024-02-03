package com.renatohack.renato_hack_dr4_at.ListagemAnotacao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListagemViewModel : ViewModel() {


    private val _file = MutableLiveData<List<String>>()
    val file: LiveData<List<String>>
        get() = _file

}