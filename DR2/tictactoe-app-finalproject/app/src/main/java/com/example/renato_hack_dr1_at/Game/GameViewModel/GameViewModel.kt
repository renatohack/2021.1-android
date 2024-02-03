package com.example.renato_hack_dr1_at.Game.GameViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.example.renato_hack_dr1_at.Game.Model.GameModel
import com.example.renato_hack_dr1_at.Game.Model.gameModel
import com.example.renato_hack_dr1_at.Menu.JogadoresObject.Jogadores

class GameViewModel : ViewModel() {

    //val gameModel : GameModel = GameModel()

    //COUNTER
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter


    //CASAS DO JOGO
    private var _posicoes = arrayOf("", "", "", "", "", "", "", "", "")
    val posicoes: Array<String>
        get() = _posicoes

    //VITORIAS E DERROTAS
    private val _vitorias_jogador_1 = MutableLiveData<Int>()
    private val _derrotas_jogador_1 = MutableLiveData<Int>()
    private val _vitorias_jogador_2 = MutableLiveData<Int>()
    private val _derrotas_jogador_2 = MutableLiveData<Int>()
    val vitorias_jogador_1 : LiveData<Int>
        get() = _vitorias_jogador_1
    val vitorias_jogador_2 : LiveData<Int>
        get() = _vitorias_jogador_2
    val derrotas_jogador_1 : LiveData<Int>
        get() = _derrotas_jogador_1
    val derrotas_jogador_2 : LiveData<Int>
        get() = _derrotas_jogador_2


    private var _ganhadorDaRodada: String = ""
    val ganhadorDaRodada: String
        get() = _ganhadorDaRodada

    //INITIALIZER
    init {
        _counter.value = 0

        _vitorias_jogador_1.value = 0
        _vitorias_jogador_2.value = 0
        _derrotas_jogador_1.value = 0
        _derrotas_jogador_2.value = 0
    }


    //ALTERAR VALOR DAS CASAS

    fun mudarCasa(casa : Int){

        when (casa){

            1 -> _posicoes[0] = gameModel.marcarPosicao()
            2 -> _posicoes[1] = gameModel.marcarPosicao()
            3 -> _posicoes[2] = gameModel.marcarPosicao()
            4 -> _posicoes[3] = gameModel.marcarPosicao()
            5 -> _posicoes[4] = gameModel.marcarPosicao()
            6 -> _posicoes[5] = gameModel.marcarPosicao()
            7 -> _posicoes[6] = gameModel.marcarPosicao()
            8 -> _posicoes[7] = gameModel.marcarPosicao()
            9 -> _posicoes[8] = gameModel.marcarPosicao()

        }

        _counter.value = _counter.value?.plus(1)

    }



    //VERIFICA SE O JOGO TERMINOU
    fun isGameFinished(): Boolean {

        if (gameModel.isGameFinished(_posicoes)) {
            _ganhadorDaRodada = gameModel.salvarGanhador()
            return true
        }
        else return false
    }


    //VERIFICA SE DEU VELHA
    fun deuVelha(): Boolean {

        if(_counter.value == 9){
            if (!gameModel.isGameFinished(_posicoes)) return true
        }
        return false
    }


    //RESETA O TABULEIRO
    fun reset() {
        _posicoes = arrayOf("", "", "", "", "", "", "", "", "")
        _counter.value = 0

        gameModel.reset()
    }

    //FAZ A CONTAGEM DAS VITORIAS E DERROTAS
    fun vitoriasEDerrotas () {

            if (gameModel.whichPlayerWon() == "Player1"){
                _vitorias_jogador_1.value = _vitorias_jogador_1.value?.plus(1)
                _derrotas_jogador_2.value = _derrotas_jogador_2.value?.plus(1)
            }
            else {
                _derrotas_jogador_1.value = _derrotas_jogador_1.value?.plus(1)
                _vitorias_jogador_2.value = _vitorias_jogador_2.value?.plus(1)
            }
    }

    fun desistir_1(){

        _derrotas_jogador_1.value = _derrotas_jogador_1.value?.plus(1)
        _vitorias_jogador_2.value = _vitorias_jogador_2.value?.plus(1)

        reset()
        gameModel.reset()
    }

    fun desistir_2(){

        _derrotas_jogador_2.value = _derrotas_jogador_2.value?.plus(1)
        _vitorias_jogador_1.value = _vitorias_jogador_1.value?.plus(1)

        reset()
        gameModel.reset()
    }




}