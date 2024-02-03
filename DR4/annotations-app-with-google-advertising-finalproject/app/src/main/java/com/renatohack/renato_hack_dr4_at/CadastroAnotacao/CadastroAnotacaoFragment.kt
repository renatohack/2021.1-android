package com.renatohack.renato_hack_dr4_at.CadastroAnotacao

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.google.android.material.snackbar.Snackbar
import com.renatohack.renato_hack_dr4_at.Login.Cadastro.CadastroViewModel
import com.renatohack.renato_hack_dr4_at.MainActivity
import com.renatohack.renato_hack_dr4_at.R
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class CadastroAnotacaoFragment : Fragment() {

    private val REQUEST_CODE_IMAGE_CAPTURE = 100
    private val REQUEST_CODE_GET_CONTENT = 200

    private lateinit var viewModel: CadastroAnotacaoViewModel
    private lateinit var viewModelFactory: CadastroAnotacaoViewModelFactory
    private lateinit var editTextTituloCadastroAnotacao : EditText
    private lateinit var editAnotacaoCadastroAnotacao : EditText
    private lateinit var buttonTirarFoto : Button
    private lateinit var buttonSalvarAnotacao : Button
    private lateinit var imageViewFotoAnotacao: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.cadastro_anotacao_fragment, container, false)

        setFragment(view)
        setViews(view)
        setListeners()

        viewModel.status.observe(viewLifecycleOwner){ status ->
            if(status){
                findNavController().popBackStack()
            }
        }

        viewModel.msg.observe(viewLifecycleOwner){ msg ->
            showSnackbar(view, msg)
        }

        viewModel.latitude.observe(viewLifecycleOwner){latitude ->
            if (!viewModel.latitude.value.isNullOrBlank() and !viewModel.longitude.value.isNullOrBlank())
            {
                viewModel.callAccessLocation()
                val title = editTextTituloCadastroAnotacao.text.toString()
                val anotation = editAnotacaoCadastroAnotacao.text.toString()
                val photo = imageViewFotoAnotacao.drawToBitmap()
                viewModel.clickGravar(title, anotation, photo)
            }
        }


        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){

            if (requestCode == REQUEST_CODE_IMAGE_CAPTURE) {
                val foto : Bitmap? = data!!.getParcelableExtra("data")
                imageViewFotoAnotacao.setImageBitmap(foto)

            } else if (requestCode == REQUEST_CODE_GET_CONTENT){
                val foto : Uri? = data!!.data!!
                if (foto != null)
                    imageViewFotoAnotacao.setImageURI(foto)
            }

        } else if (resultCode == Activity.RESULT_OK){
            showSnackbar(requireView(), "Ocorreu um problema!")
        }
    }

    private fun setListeners() {

        buttonTirarFoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE)
        }

        buttonSalvarAnotacao.setOnClickListener {
            if (!editAnotacaoCadastroAnotacao.text.isNullOrBlank() && !editTextTituloCadastroAnotacao.text.isNullOrBlank()){
                if (imageViewFotoAnotacao.drawable != null){
                    viewModel.callAccessLocation()
                }
                else showSnackbar(requireView(), "Você precisa tirar uma foto!")
            }
            else showSnackbar(requireView(), "Você precisa preencher todos os campos!")
        }
    }

    private fun setFragment(view: View?) {
        viewModelFactory = CadastroAnotacaoViewModelFactory(requireContext(), requireActivity() as MainActivity)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CadastroAnotacaoViewModel::class.java)
    }

    fun setViews(view : View){
        buttonTirarFoto = view.findViewById(R.id.buttonTirarFoto)
        buttonSalvarAnotacao = view.findViewById(R.id.buttonSalvarAnotacao)
        editAnotacaoCadastroAnotacao = view.findViewById(R.id.editAnotacaoCadastroAnotacao)
        editTextTituloCadastroAnotacao = view.findViewById(R.id.editTextTituloCadastroAnotacao)
        imageViewFotoAnotacao = view.findViewById(R.id.imageViewFotoAnotacao)

    }


    private fun showSnackbar(view: View, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, msg, duration).show()
    }



}