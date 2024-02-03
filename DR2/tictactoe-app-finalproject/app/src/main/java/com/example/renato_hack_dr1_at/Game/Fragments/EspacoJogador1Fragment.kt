package com.example.renato_hack_dr1_at.Game.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.renato_hack_dr1_at.Game.Activity.JogoActivity
import com.example.renato_hack_dr1_at.Game.Activity.viewModelGameActivity
import com.example.renato_hack_dr1_at.Game.GameViewModel.GameViewModel
import com.example.renato_hack_dr1_at.Menu.JogadoresObject.Jogadores
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_espaco_jogador1.*


class EspacoJogador1Fragment : Fragment() {

    private lateinit var viewModelGameJogador1 : GameViewModel   // by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelGameJogador1 = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        viewModelGameJogador1.vitorias_jogador_1.observe(viewLifecycleOwner, Observer {
            vitorias_jogador_1.text = it.toString()
        })

        viewModelGameJogador1.derrotas_jogador_1.observe(viewLifecycleOwner, Observer {
            derrotas_jogador_1.text = it.toString()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_espaco_jogador1, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nome_jogador1.text = Jogadores.jogador_1

        button_desistir_jogador_1.setOnClickListener {

            viewModelGameJogador1.desistir_1()

        }

    }
}