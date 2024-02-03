package com.renatohack.renato_hack_dr4_at.Login.Entrar

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

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var editTextEmailLogin : EditText
    private lateinit var editTextPasswordLogin : EditText
    private lateinit var buttonEntrarLogin : Button
    private lateinit var buttonCadastrarLogin : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.login_fragment, container, false)

        setFragment(view)
        setViews(view)
        setListeners()

        viewModel.status.observe(viewLifecycleOwner, { status ->
            if (status) {
                val nav = findNavController()
                nav.navigate(R.id.action_loginFragment_to_telaPrincipalFragment)
            }
        })

        viewModel.msg.observe(viewLifecycleOwner, { msg ->
            showSnackbar(view, msg)
            changeEnable()
        })

        return view
    }

    private fun setViews(view: View){
        buttonEntrarLogin = view.findViewById(R.id.buttonEntrarLogin)
        editTextEmailLogin = view.findViewById(R.id.editTextEmailLogin)
        editTextPasswordLogin = view.findViewById(R.id.editTextPasswordLogin)
        buttonCadastrarLogin = view.findViewById(R.id.buttonCadastrarLogin)

    }

    private fun setFragment(view: View){
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun setListeners(){

        buttonEntrarLogin.setOnClickListener {

            val email = editTextEmailLogin.text.toString()
            val senha = editTextPasswordLogin.text.toString()

            changeEnable(false)
            viewModel.login(email, senha)

        }

        buttonCadastrarLogin.setOnClickListener{
            val nav = findNavController()
            nav.navigate(R.id.action_loginFragment_to_cadastroFragment)
        }
    }

    private fun showSnackbar(view: View, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, msg, duration).show()
    }

    // Apenas para bloquear os elementos de acesso
    private fun changeEnable(isEnabled: Boolean = true) {
        editTextEmailLogin.isEnabled = isEnabled
        editTextPasswordLogin.isEnabled = isEnabled
        buttonEntrarLogin.isEnabled = isEnabled
        buttonCadastrarLogin.isEnabled = isEnabled
    }
}