package renatohack.minhascompras.ElementosDaListaAberta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import renatohack.minhascompras.Database.AppDatabase
import renatohack.minhascompras.Database.Dao.ElementDao
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.ElementosDaListaAberta.Adapter.ElementsListsRecyclerViewAdapter
import renatohack.minhascompras.R

class ElementosDaListaAberta : Fragment() {

    private lateinit var viewModel : ElementosDaListaAbertaViewModel
    private lateinit var viewModelFactory: ElementosDaListaAbertaViewModelFactory

    private lateinit var recycler : RecyclerView

    private lateinit var appDatabase : AppDatabase
    private lateinit var listDao : ListDao
    private lateinit var elementDao : ElementDao


    private lateinit var textViewNomeLista : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_elementos_da_lista_aberta, container, false)

        val listID = arguments?.getString("id").toString()

        setFragment(view, listID)
        setViews(view)

        viewModel.name.observe(viewLifecycleOwner) {
            textViewNomeLista.text = viewModel.name.value
        }

        viewModel.element.observe(viewLifecycleOwner) {
            recycler.adapter = ElementsListsRecyclerViewAdapter(viewModel.element.value!!, elementDao, listID)
        }

        // Inflate the layout for this fragment
        return view
    }


    private fun setFragment(view: View, listID: String?) {
        appDatabase = AppDatabase.getInstance(requireContext().applicationContext)
        listDao = appDatabase.ListaDao()
        elementDao = appDatabase.ElementosDao()

        //VIEWMODEL
        viewModelFactory = listID?.let { ElementosDaListaAbertaViewModelFactory(listDao, it) }!!

        viewModel = ViewModelProvider(this, viewModelFactory).get(ElementosDaListaAbertaViewModel::class.java)
    }

    private fun setViews(view : View){
        textViewNomeLista = view.findViewById(R.id.textViewNomeLista)
        recycler = view.findViewById(R.id.recyclerListaElementos)
    }

}