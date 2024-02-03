package com.renatohack.renato_hack_dr4_at.ListagemAnotacao.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.renatohack.renato_hack_dr4_at.R

class ListagemAdapter(val files : MutableList<String>, private val actionClick: (id : String) -> Unit ) : RecyclerView.Adapter<ListagemAdapter.ListagemViewHolder>() {


    class ListagemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val textViewTituloRecycler : TextView = view.findViewById(R.id.textViewTituloRecycler)
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onBindViewHolder(holder: ListagemViewHolder, position: Int) {
        val file = files[position]

        holder.textViewTituloRecycler.text = file
        holder.itemView.setOnClickListener { itemView ->
            actionClick(file)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListagemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listagem_modelo, parent, false)

        return ListagemViewHolder(view)
    }

}