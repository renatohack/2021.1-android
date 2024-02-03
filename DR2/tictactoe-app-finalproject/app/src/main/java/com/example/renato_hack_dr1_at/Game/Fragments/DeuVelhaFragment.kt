package com.example.renato_hack_dr1_at.Game.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.renato_hack_dr1_at.Game.Activity.viewModelGameActivity
import com.example.renato_hack_dr1_at.Game.Fragments.ResultadoJogoFragmentDirections
import com.example.renato_hack_dr1_at.Game.GameViewModel.GameViewModel
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_deu_velha.*


class DeuVelhaFragment : Fragment() {

    private lateinit var viewModelDeuVelha : GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelDeuVelha = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deu_velha, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_jogar_novamente_deuvelha.setOnClickListener { jogarNovamente() }
    }

    fun jogarNovamente (){
        val navController = findNavController()
        val action = DeuVelhaFragmentDirections.actionDeuVelhaFragmentToEspacoJogoFragment()
        viewModelDeuVelha.reset()
        navController.navigate(action)
    }

}