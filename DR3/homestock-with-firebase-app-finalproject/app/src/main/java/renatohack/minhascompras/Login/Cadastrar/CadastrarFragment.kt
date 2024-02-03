package renatohack.minhascompras.Login.Cadastrar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import renatohack.minhascompras.Login.Entrar.LoginFragmentViewModel
import renatohack.minhascompras.R

class CadastrarFragment : Fragment() {

    private lateinit var viewModel: CadastrarViewModel

    private lateinit var editTextEmailCadastrar : EditText
    private lateinit var editTextSenhaCadastrar : EditText
    private lateinit var editTextRepetirSenhaCadastrar : EditText
    private lateinit var buttonCadastrarCadastrar : Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cadastrar, container, false)

        setFragment(view)
        setViews(view)
        setListeners()

        viewModel.msg.observe(viewLifecycleOwner, { msg ->
            showSnackbar(view, msg)
            changeEnable()
        })

        viewModel.status.observe(viewLifecycleOwner, { status ->
            if (status){
                val nav = findNavController()
                nav.navigate(R.id.action_cadastrarFragment_to_telaPrincipalFragment)
            }
        })

        return view
    }

    private fun setListeners() {

        buttonCadastrarCadastrar.setOnClickListener {

            val email = editTextEmailCadastrar.text.toString()
            val senha = editTextSenhaCadastrar.text.toString()
            val repetirSenha = editTextRepetirSenhaCadastrar.text.toString()

            changeEnable(false)
            viewModel.createUser(email, senha, repetirSenha)
        }
    }

    private fun setFragment(view: View?) {
        viewModel = ViewModelProvider(this).get(CadastrarViewModel::class.java)
    }

    fun setViews(view : View){
        editTextEmailCadastrar = view.findViewById(R.id.editTextEmailCadastrar)
        editTextSenhaCadastrar = view.findViewById(R.id.editTextSenhaCadastrar)
        editTextRepetirSenhaCadastrar = view.findViewById(R.id.editTextRepetirSenhaCadastrar)
        buttonCadastrarCadastrar = view.findViewById(R.id.buttonCadastrarCadastrar)
    }


    private fun showSnackbar(view: View, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, msg, duration).show()
    }

    // Apenas para bloquear os elementos de acesso
    private fun changeEnable(isEnabled: Boolean = true) {
        editTextEmailCadastrar.isEnabled = isEnabled
        editTextSenhaCadastrar.isEnabled = isEnabled
        editTextRepetirSenhaCadastrar.isEnabled = isEnabled
        buttonCadastrarCadastrar.isEnabled = isEnabled
    }
}