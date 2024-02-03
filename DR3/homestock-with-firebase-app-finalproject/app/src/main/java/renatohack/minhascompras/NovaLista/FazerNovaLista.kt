package renatohack.minhascompras.NovaLista

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import renatohack.minhascompras.Database.AppDatabase
import renatohack.minhascompras.Database.Dao.ElementDao
import renatohack.minhascompras.Database.Dao.ListDao
import renatohack.minhascompras.Database.Dao.TempDao
import renatohack.minhascompras.Model.Firestore.Elemento
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.Model.Firestore.ListaFirebase
import renatohack.minhascompras.Model.Room.Lista
import renatohack.minhascompras.NovaLista.Adapter.TempRecyclerViewAdapter
import renatohack.minhascompras.R


class FazerNovaLista : Fragment() {

    private lateinit var viewModel : FazerNovaListaViewModel
    private lateinit var viewModelFactory: FazerNovaListaViewModelFactory

    private lateinit var recycler : RecyclerView
    private lateinit var fabAlocarElemento : FloatingActionButton
    private lateinit var buttonSalvarLista : Button
    private lateinit var editTextCriarNomeLista : EditText

    private lateinit var appDatabase : AppDatabase
    private lateinit var tempDao : TempDao
    private lateinit var listDao : ListDao
    private lateinit var elementDao : ElementDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fazer_nova_lista, container, false)

        initializeFragment(view)


        viewModel.element.observe(viewLifecycleOwner) {
            recycler.adapter = TempRecyclerViewAdapter(viewModel.element.value!!, tempDao)
        }

        setListeners(view)

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.updateList()

    }

    private fun initializeFragment(view: View) {
        //DATABASE
        appDatabase = AppDatabase.getInstance(requireContext().applicationContext)
        tempDao = appDatabase.tempDao()
        listDao = appDatabase.ListaDao()
        elementDao = appDatabase.ElementosDao()

        //VIEWMODEL
        viewModelFactory = FazerNovaListaViewModelFactory(tempDao, listDao, elementDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FazerNovaListaViewModel::class.java)

        //VIEWS
        recycler = view.findViewById(R.id.recyclerCriarLista)

        fabAlocarElemento = view.findViewById(R.id.fabAlocarElemento)
        buttonSalvarLista = view.findViewById(R.id.buttonSalvarListaNovaLista)
        editTextCriarNomeLista = view.findViewById(R.id.editTextCriarNomeLista)
    }

    private fun hideKeyboard(view: View){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setListeners(view: View) {
        fabAlocarElemento.setOnClickListener {
            val nav = findNavController()
            nav.navigate(R.id.action_fazerNovaLista_to_criarElemento)
        }

        buttonSalvarLista.setOnClickListener {
                if (!editTextCriarNomeLista.text.isNullOrBlank() && !viewModel.element.value.isNullOrEmpty()) {
                    val listName = editTextCriarNomeLista.text.toString().toUpperCase()
                    val elementosLista: MutableList<Elemento> = viewModel.tempToElements()

                    val lista = ListaFirebase(listName, elementosLista)

                    viewModel.insertList(lista)
//                    viewModel.updateElementsInList()
                    viewModel.deleteAllTemps()
//                    viewModel.updateList()

                    val nav = findNavController()
                    nav.navigate(R.id.action_fazerNovaLista_to_telaPrincipalFragment)
                }

            hideKeyboard(view)
        }
    }

}