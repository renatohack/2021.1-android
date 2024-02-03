package com.example.renato_hack_dr1_at.Menu.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.renato_hack_dr1_at.R
import com.example.renato_hack_dr1_at.adapter.PontuacaoRecyclerViewAdapter
import com.example.renato_hack_dr1_at.adapter.PontuacaoViewModel
import kotlinx.android.synthetic.main.fragment_pontuacao.view.*


class PontuacaoFragment : Fragment() {

    private lateinit var pontuacaoViewModel : PontuacaoViewModel
    private lateinit var recyclerView_pontuacao : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pontuacao, container, false)

        recyclerView_pontuacao = view.findViewById(R.id.recyclerView_pontuacao)

        pontuacaoViewModel = ViewModelProvider(this).get(PontuacaoViewModel::class.java)

        pontuacaoViewModel.pontuacoes.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()){
                recyclerView_pontuacao.adapter = PontuacaoRecyclerViewAdapter(it)
            }
        }
        )


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}