package renatohack.minhascompras.Login.Entrar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import renatohack.minhascompras.R


class LoginFragment : Fragment() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var buttonEntrarLogin : Button
    private lateinit var editTextEmailLogin : EditText
    private lateinit var editTextSenhaLogin : EditText
    private lateinit var buttonCadastrarLogin : Button
    private lateinit var buttonFacebookLogin : LoginButton

    private lateinit var viewModel : LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        setFragment(view)
        setViews(view)
        setListeners()

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        buttonFacebookLogin.setReadPermissions("email", "public_profile")
        buttonFacebookLogin.fragment = this
        buttonFacebookLogin.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.i("FACEBOOK", "LOGIN SUCESSO!!! facebook:onSuccess:$loginResult")
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.i("FACEBOOK", "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.i("FACEBOOK", "facebook:onError", error)
                }
        })




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

        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun setViews(view: View){
        buttonEntrarLogin = view.findViewById(R.id.buttonEntrarLogin)
        editTextEmailLogin = view.findViewById(R.id.editTextEmailLogin)
        editTextSenhaLogin = view.findViewById(R.id.editTextSenhaLogin)
        buttonCadastrarLogin = view.findViewById(R.id.buttonCadastrarLogin)
        buttonFacebookLogin = view.findViewById(R.id.buttonFacebookLogin)
    }

    private fun setFragment(view: View){
        viewModel = ViewModelProvider(this).get(LoginFragmentViewModel::class.java)
    }

    private fun setListeners(){

        buttonEntrarLogin.setOnClickListener {

            val email = editTextEmailLogin.text.toString()
            val senha = editTextSenhaLogin.text.toString()

            changeEnable(false)
            viewModel.login(email, senha)

        }

        buttonCadastrarLogin.setOnClickListener{
            val nav = findNavController()
            nav.navigate(R.id.action_loginFragment_to_cadastrarFragment)
        }
    }

    private fun showSnackbar(view: View, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, msg, duration).show()
    }

    // Apenas para bloquear os elementos de acesso
    private fun changeEnable(isEnabled: Boolean = true) {
        editTextEmailLogin.isEnabled = isEnabled
        editTextSenhaLogin.isEnabled = isEnabled
        buttonEntrarLogin.isEnabled = isEnabled
        buttonCadastrarLogin.isEnabled = isEnabled
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("FACEBOOK", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        viewModel.login(credential)
    }

}