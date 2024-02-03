package com.example.renato_hack_dr1_at.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.renato_hack_dr1_at.R
import kotlinx.android.synthetic.main.fragment_pontuacao.view.*
import kotlinx.android.synthetic.main.layout_modelo_recyclerview.view.*

class PontuacaoRecyclerViewAdapter (
    private val pontuacoes : List <Pontuacao>
) : RecyclerView.Adapter<PontuacaoRecyclerViewAdapter.PontuacaoViewHolder>(){

    class PontuacaoViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val jogador_1 : TextView = view.recyclerView_jogador_1_textView
        val vitorias_1 : TextView = view.recyclerView_vitorias_jogador_1_textView
        val jogador_2 : TextView = view.recyclerView_jogador_2_textView
        val vitorias_2 : TextView = view.recyclerView_vitorias_jogador_2_textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PontuacaoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_modelo_recyclerview, parent, false)

        return PontuacaoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return pontuacoes.size
    }

    override fun onBindViewHolder(holder: PontuacaoViewHolder, position: Int) {

        val pontuacao = pontuacoes[position]
        holder.jogador_1.text = pontuacao.jogador_1
        holder.vitorias_1.text = pontuacao.vitorias_jogador_1.toString()
        holder.jogador_2.text = pontuacao.jogador_2
        holder.vitorias_2.text = pontuacao.vitorias_jogador_2.toString()

    }
}