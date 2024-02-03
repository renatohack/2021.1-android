package com.example.renato_hack_dr1_at.Game.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.renato_hack_dr1_at.Game.GameViewModel.GameViewModel
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_espaco_jogo.*
import kotlinx.android.synthetic.main.fragment_main_menu.view.*


class EspacoJogoFragment : Fragment() {

    //val viewModelGame : GameViewModel by activityViewModels()

    private lateinit var viewModelGame : GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelGame = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        viewModelGame.reset()

        viewModelGame.counter.observe(viewLifecycleOwner, Observer {
//
            posicao_1.text = viewModelGame.posicoes[0]
            posicao_2.text = viewModelGame.posicoes[1]
            posicao_3.text = viewModelGame.posicoes[2]
            posicao_4.text = viewModelGame.posicoes[3]
            posicao_5.text = viewModelGame.posicoes[4]
            posicao_6.text = viewModelGame.posicoes[5]
            posicao_7.text = viewModelGame.posicoes[6]
            posicao_8.text = viewModelGame.posicoes[7]
            posicao_9.text = viewModelGame.posicoes[8]


            if (viewModelGame.deuVelha()) {
                val navController = findNavController()
                val action =
                    EspacoJogoFragmentDirections.actionEspacoJogoFragmentToDeuVelhaFragment()
                navController.navigate(action)
            }

        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_espaco_jogo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()

    }


    fun setClickListeners(){
        posicao_1.setOnClickListener {
            if (posicao_1.text != "X" && posicao_1.text != "O"){
                viewModelGame.mudarCasa(1)
                telaResultado()
            }
        }

        posicao_2.setOnClickListener {
            if (posicao_2.text != "X" && posicao_2.text != "O"){
                viewModelGame.mudarCasa(2)
                telaResultado()
            }
        }

        posicao_3.setOnClickListener {
            if (posicao_3.text != "X" && posicao_3.text != "O"){
                viewModelGame.mudarCasa(3)
                telaResultado()
            }
        }

        posicao_4.setOnClickListener {
            if (posicao_4.text != "X" && posicao_4.text != "O"){
                viewModelGame.mudarCasa(4)
                telaResultado()
            }
        }

        posicao_5.setOnClickListener {
            if (posicao_5.text != "X" && posicao_5.text != "O"){
                viewModelGame.mudarCasa(5)
                telaResultado()
            }
        }

        posicao_6.setOnClickListener {
            if (posicao_6.text != "X" && posicao_6.text != "O"){
                viewModelGame.mudarCasa(6)
                telaResultado()
            }
        }

        posicao_7.setOnClickListener {
            if (posicao_7.text != "X" && posicao_7.text != "O"){
                viewModelGame.mudarCasa(7)
                telaResultado()
            }
        }

        posicao_8.setOnClickListener {
            if (posicao_8.text != "X" && posicao_8.text != "O"){
                viewModelGame.mudarCasa(8)
                telaResultado()
            }
        }

        posicao_9.setOnClickListener {
            if (posicao_9.text != "X" && posicao_9.text != "O"){
                viewModelGame.mudarCasa(9)
                telaResultado()
            }
        }
    }

    fun telaResultado(){
        if (viewModelGame.isGameFinished()){

            val navController = findNavController()
            val action =
                EspacoJogoFragmentDirections.actionEspacoJogoFragmentToResultadoJogoFragment(viewModelGame.ganhadorDaRodada)

            viewModelGame.vitoriasEDerrotas()

            navController.navigate(action)
        }
    }

}