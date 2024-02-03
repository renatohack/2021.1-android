package com.example.renato_hack_dr1_at.Menu.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.renato_hack_dr1_at.Game.Activity.JogoActivity
import com.example.renato_hack_dr1_at.Menu.JogadoresObject.Jogadores
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_menu_jogadores.*


class MenuJogadoresFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_jogadores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_comecar_jogo.setOnClickListener {

            Jogadores.jogador_1 = editText_jogador1.text.toString()
            Jogadores.jogador_2 = editText_jogador2.text.toString()

           comecar_jogo_activity()
        }

    }

    private fun comecar_jogo_activity() {

        val jogador_1 = editText_jogador1.text.toString()
        val jogador_2 = editText_jogador2.text.toString()
        if (jogador_1 != "" && jogador_2 != "" && jogador_1 != jogador_2) {
            val intent = Intent(requireContext(), JogoActivity::class.java)
            startActivity(intent)
        }
    }
}