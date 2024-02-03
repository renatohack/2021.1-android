package com.example.renato_hack_dr1_at.Menu.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.renato_hack_dr1_at.Menu.Fragments.MainMenuFragmentDirections
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_main_menu.*


class MainMenuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
    }


    private fun setClickListeners() {
        button_jogar.setOnClickListener {

            irParaJogar()

        }

        sobre_textView.setOnClickListener {

            irParaSobre()
        }

        pontuacao_textView.setOnClickListener {

            irParaPontuacao()
        }

        comoJogar_textView.setOnClickListener {
            comoJogar()
        }
    }


        private fun irParaJogar() {
            val navController = findNavController()
            val action =
                MainMenuFragmentDirections.actionMainMenuToMenuJogadores()
            navController.navigate(action)
        }

        private fun irParaSobre() {
            val navController = findNavController()

            val action = MainMenuFragmentDirections.actionMainMenuToSobreFragment()

            navController.navigate(action)
        }

        private fun irParaPontuacao() {
            val navController = findNavController()

            val action = MainMenuFragmentDirections.actionMainMenuToPontuacaoFragment()

            navController.navigate(action)
        }

        fun comoJogar() {
            val webIntent: Intent =
                Uri.parse("https://pt.wikihow.com/Jogar-Jogo-da-Velha").let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)
                }

            if (webIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(webIntent)
            }
        }




}