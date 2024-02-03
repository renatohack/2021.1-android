package com.example.renato_hack_dr1_at.Game.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.renato_hack_dr1_at.Game.Activity.viewModelGameActivity
import com.example.renato_hack_dr1_at.Game.Fragments.ResultadoJogoFragmentArgs
import com.example.renato_hack_dr1_at.Game.Fragments.ResultadoJogoFragmentDirections
import com.example.renato_hack_dr1_at.Game.GameViewModel.GameViewModel
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_resultado_jogo.*




class ResultadoJogoFragment : Fragment() {

    private lateinit var viewModelResultado : GameViewModel

    val args : ResultadoJogoFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModelResultado = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resultado_jogo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ganhadorTextView.text = args.jogadorAtual.toString()

        button_jogar_novamente.setOnClickListener { jogarNovamente() }
    }

    fun jogarNovamente (){
        val navController = findNavController()
        val action =
            ResultadoJogoFragmentDirections.actionResultadoJogoFragmentToEspacoJogoFragment()
        viewModelResultado.reset()
        navController.navigate(action)
    }




}