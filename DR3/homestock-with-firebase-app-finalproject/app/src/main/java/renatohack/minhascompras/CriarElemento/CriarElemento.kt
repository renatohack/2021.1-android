package renatohack.minhascompras.CriarElemento

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import renatohack.minhascompras.Database.AppDatabase
import renatohack.minhascompras.Firebase.TemporaryDao
import renatohack.minhascompras.Model.Firestore.ElementoTemporario
import renatohack.minhascompras.R



class CriarElemento : Fragment() {

    private lateinit var item : EditText
    private lateinit var quantidade : Spinner
    private lateinit var preco : EditText
    private lateinit var buttonTotal : Button
    private lateinit var buttonSalvarElemento : Button
    private lateinit var totalText : TextView

    private lateinit var viewModel : CriarElementoViewModel
    private lateinit var viewModelFactory : CriarElementoViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_criar_elemento, container, false)

        setViews(view)
        initializeFragment()

        setListeners(view)

        viewModel.msg.observe(viewLifecycleOwner, { msg ->
            showSnackbar(view, msg)
        })

        viewModel.status.observe(viewLifecycleOwner, { status ->
            if (status) {
                val nav = findNavController()
                nav.popBackStack()
            }
        })



        // Inflate the layout for this fragment
        return view
    }

    private fun setListeners(view: View) {
        buttonTotal.setOnClickListener {
            if (quantidade.selectedItem.toString() != "" && preco.text.toString() != "" && item.text.toString() != "") {
                totalText.text =
                    (preco.text.toString().toFloat() * quantidade.selectedItem.toString()
                        .toInt()).toString()
            } else {
                Snackbar.make(
                    requireContext(),
                    view,
                    "Você precisa preencher todos os campos.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            hideKeyboard(view)
        }

        buttonSalvarElemento.setOnClickListener {
            if (quantidade.selectedItem.toString() != "" && preco.text.toString() != "" && item.text.toString() != "") {

                val name = item.text.toString().toUpperCase()
                val price = preco.text.toString()
                val quantity = quantidade.selectedItem.toString()
                val temp = ElementoTemporario(name, price, quantity)

                viewModel.insertTemporaryDao(temp)
            } else {
                Snackbar.make(
                    requireContext(),
                    view,
                    "Você precisa preencher todos os campos.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            hideKeyboard(view)
        }
    }

    private fun initializeFragment() {
        val appDatabase = AppDatabase.getInstance(requireContext().applicationContext)
        val tempDao = appDatabase.tempDao()
        viewModelFactory = CriarElementoViewModelFactory(tempDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CriarElementoViewModel::class.java)
    }

    private fun setViews(view: View){
        item = view.findViewById<EditText>(R.id.editTextItemCriarElemento)
        quantidade = view.findViewById<Spinner>(R.id.spinnerQuantidadeCriarElemento)
        preco = view.findViewById<EditText>(R.id.editTextPrecoCriarElemento)
        buttonTotal = view.findViewById<Button>(R.id.buttonCalcularTotalCriarElemento)
        buttonSalvarElemento = view.findViewById<Button>(R.id.buttonSalvarItemCriarElemento)
        totalText = view.findViewById(R.id.textViewTotalCriarElemento)
    }

    private fun hideKeyboard(view: View){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showSnackbar(view: View, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, msg, duration).show()
    }
}