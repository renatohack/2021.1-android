package renatohack.minhascompras.ListasAbertas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import renatohack.minhascompras.Database.AppDatabase
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.ListasAbertas.Adapter.NewListsRecyclerViewAdapter
import renatohack.minhascompras.Logger.Logger
import renatohack.minhascompras.R

class ListasAbertas : Fragment() {

    private lateinit var viewModel : ListasAbertasViewModel
    private lateinit var viewModelFactory: ListasAbertasViewModelFactory
    private lateinit var logger : Logger

    private lateinit var recycler : RecyclerView
    private lateinit var buttonSalvarListasEmTexto : Button

    private lateinit var appDatabase : AppDatabase
    private lateinit var listDao : ListDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_listas_abertas, container, false)

        initalizeFragment(view)

        viewModel.lista.observe(viewLifecycleOwner) {
            recycler.adapter = NewListsRecyclerViewAdapter(viewModel.lista.value!!, listDao){id ->
                val args = bundleOf("id" to id)
                findNavController().navigate(R.id.action_listasAbertas_to_elementosDaListaAberta, args)
            }
        }

//        buttonSalvarListasEmTexto.setOnClickListener {
//
//            GlobalScope.launch{ logger.write(viewModel.listElementsToString()) }
//        }

        return view
    }

    private fun initalizeFragment(view: View) {

        //DATABASE
        appDatabase = AppDatabase.getInstance(requireContext().applicationContext)
        listDao = appDatabase.ListaDao()

        //VIEWMODEL
        viewModelFactory = ListasAbertasViewModelFactory(listDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListasAbertasViewModel::class.java)

        //LOGGER
        logger = Logger(requireContext())

        //VIEWS
        recycler = view.findViewById(R.id.recyclerListasEmAberto)
        buttonSalvarListasEmTexto = view.findViewById(R.id.buttonSalvarListasEmTexto)
    }
}