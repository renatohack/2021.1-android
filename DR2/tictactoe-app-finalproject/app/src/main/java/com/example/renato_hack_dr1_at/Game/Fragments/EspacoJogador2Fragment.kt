package com.example.renato_hack_dr1_at.Game.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.renato_hack_dr1_at.Game.Activity.viewModelGameActivity
import com.example.renato_hack_dr1_at.Game.GameViewModel.GameViewModel
import com.example.renato_hack_dr1_at.Menu.JogadoresObject.Jogadores
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_espaco_jogador1.*
import kotlinx.android.synthetic.main.fragment_espaco_jogador2.*


class EspacoJogador2Fragment : Fragment() {

    private lateinit var viewModelGameJogador2 : GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelGameJogador2 = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        viewModelGameJogador2.vitorias_jogador_2.observe(viewLifecycleOwner, Observer {
            vitorias_jogador_2.text = it.toString()
        })

        viewModelGameJogador2.derrotas_jogador_2.observe(viewLifecycleOwner, Observer {
            derrotas_jogador_2.text = it.toString()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_espaco_jogador2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nome_jogador2.text = Jogadores.jogador_2

        button_desistir_jogador_2.setOnClickListener {

            viewModelGameJogador2.desistir_2()

        }
    }
}