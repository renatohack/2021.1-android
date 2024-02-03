package com.renatohack.renato_hack_dr4_at.Login.Cadastro

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
import com.renatohack.renato_hack_dr4_at.R

class CadastroFragment : Fragment() {



    private lateinit var viewModel: CadastroViewModel

    private lateinit var editTextEmailCadastro : EditText
    private lateinit var editTextSenhaCadastro : EditText
    private lateinit var editTextRepetirSenhaCadastro : EditText
    private lateinit var buttonCadastrarCadastro : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.cadastro_fragment, container, false)

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
                nav.navigate(R.id.action_cadastroFragment_to_telaPrincipalFragment)
            }
        })


        return view
    }


    private fun setListeners() {

        buttonCadastrarCadastro.setOnClickListener {

            val email = editTextEmailCadastro.text.toString()
            val senha = editTextSenhaCadastro.text.toString()
            val repetirSenha = editTextRepetirSenhaCadastro.text.toString()

            changeEnable(false)
            viewModel.createUser(email, senha, repetirSenha)
        }
    }

    private fun setFragment(view: View?) {
        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)
    }

    fun setViews(view : View){
        editTextEmailCadastro = view.findViewById(R.id.editTextEmailCadastro)
        editTextSenhaCadastro = view.findViewById(R.id.editTextPasswordCadastro)
        editTextRepetirSenhaCadastro = view.findViewById(R.id.editTextPasswordRepeatedCadastro)
        buttonCadastrarCadastro = view.findViewById(R.id.buttonCadastrarCadastro)
    }


    private fun showSnackbar(view: View, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, msg, duration).show()
    }

    // Apenas para bloquear os elementos de acesso
    private fun changeEnable(isEnabled: Boolean = true) {
        editTextEmailCadastro.isEnabled = isEnabled
        editTextSenhaCadastro.isEnabled = isEnabled
        editTextRepetirSenhaCadastro.isEnabled = isEnabled
        buttonCadastrarCadastro.isEnabled = isEnabled
    }

}