package com.example.renato_hack_dr1_at.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PontuacaoViewModel : ViewModel(){

    private val _pontuacoes = MutableLiveData<List<Pontuacao>>()
    val pontuacoes : LiveData<List<Pontuacao>>
        get() = _pontuacoes


    init {

        _pontuacoes.value = ListaDePontuacoes.all()

    }

}