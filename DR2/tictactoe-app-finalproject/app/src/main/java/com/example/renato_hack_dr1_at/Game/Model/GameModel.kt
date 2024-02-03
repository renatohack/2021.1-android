package com.example.renato_hack_dr1_at.Game.Model

import com.example.renato_hack_dr1_at.Menu.JogadoresObject.Jogadores

object gameModel {

    private val _jogador_1 = Jogadores.jogador_1
    private val _jogador_2 = Jogadores.jogador_2

    private var _jogadorAtual: String = ""
    private var _proximoJogador: String = _jogador_1

     var nomeGanhador : String = ""


    fun marcarPosicao(): String {

        var casa = ""

        if (_proximoJogador == _jogador_1) {
            casa = "X"
            _jogadorAtual = _jogador_1
            _proximoJogador = _jogador_2
        } else if (_proximoJogador == _jogador_2) {
            casa = "O"
            _jogadorAtual = _jogador_2
            _proximoJogador = _jogador_1
        }
        return casa
    }


    fun reset() {
        _jogadorAtual = ""
        _proximoJogador = _jogador_1
    }


    fun isGameFinished(_posicoes : Array<String>): Boolean {

        val items = arrayOf("X", "O")

        for (item in items) {
            if (_posicoes[0] == _posicoes[1] && _posicoes[1] == _posicoes[2] && _posicoes[0] == item) return true
            if (_posicoes[3] == _posicoes[4] && _posicoes[4] == _posicoes[5] && _posicoes[3] == item) return true
            if (_posicoes[6] == _posicoes[7] && _posicoes[7] == _posicoes[8] && _posicoes[6] == item) return true

            if (_posicoes[0] == _posicoes[3] && _posicoes[3] == _posicoes[6] && _posicoes[0] == item) return true
            if (_posicoes[1] == _posicoes[4] && _posicoes[4] == _posicoes[7] && _posicoes[1] == item) return true
            if (_posicoes[2] == _posicoes[5] && _posicoes[5] == _posicoes[8] && _posicoes[2] == item) return true

            if (_posicoes[0] == _posicoes[4] && _posicoes[4] == _posicoes[8] && _posicoes[0] == item) return true
            if (_posicoes[2] == _posicoes[4] && _posicoes[4] == _posicoes[6] && _posicoes[2] == item) return true
        }

        return false
    }

    fun salvarGanhador(): String {
            nomeGanhador = _jogadorAtual
            return nomeGanhador
    }


    fun whichPlayerWon() : String{
        if(_jogadorAtual == _jogador_1) return "Player1"
        else return "Player2"
    }
}