package com.renatohack.renato_hack_dr4_at.ListagemAnotacao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.renatohack.renato_hack_dr4_at.ListagemAnotacao.Adapter.ListagemAdapter
import com.renatohack.renato_hack_dr4_at.R

class ListagemFragment : Fragment() {

    private lateinit var recycler : RecyclerView
    private lateinit var viewModel: ListagemViewModel
    private lateinit var textViewUsuarioListagem : TextView
    private lateinit var buttonLogoutListagem: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.listagem_fragment, container, false)

        val files = requireContext().filesDir.list().toList().filter { arquivo -> arquivo.split(".")[1] == "txt" }
        val filesFiltrado: MutableList<String> = files.toMutableList()

        for(i in 0 until filesFiltrado.size){
            filesFiltrado[i] = filesFiltrado[i].split(".")[0]
        }
        recycler = view.findViewById(R.id.recyclerViewListagem)
        textViewUsuarioListagem = view.findViewById(R.id.textViewUsuarioListagem)
        buttonLogoutListagem = view.findViewById(R.id.buttonLogoutListagem)

        textViewUsuarioListagem.text = Firebase.auth.currentUser!!.email

        buttonLogoutListagem.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_listagemFragment_to_loginFragment)
        }


        recycler.adapter = ListagemAdapter(filesFiltrado){ file ->
            val args = bundleOf("file" to file)
            findNavController().navigate(R.id.action_listagemFragment_to_detalhesAnotacaoFragment, args)
        }

        return view
    }


}