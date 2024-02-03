package com.renatohack.renato_hack_dr4_at.DealhesAnotacao

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.renatohack.renato_hack_dr4_at.R
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class DetalhesAnotacaoFragment : Fragment() {

    private lateinit var viewModel: DetalhesAnotacaoViewModel
    private lateinit var titulo : TextView
    private lateinit var anotacao : TextView
    private lateinit var imagem : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detalhes_anotacao_fragment, container, false)

        val arquivo = arguments?.getString("file").toString()

        titulo = view.findViewById(R.id.textViewTituloDetalhes)
        anotacao = view.findViewById(R.id.textViewAnotacaoDetalhes)
        imagem = view.findViewById(R.id.imageViewDetalhes)

        titulo.text = arquivo
        anotacao.text = clickLerTexto(arquivo)
        imagem.setImageBitmap(clickLerImagem(arquivo))

        return view
    }

    private fun getEncFile(nome: String): EncryptedFile {
        val masterKeyAlias: String =
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val file: java.io.File =
            java.io.File(requireContext().filesDir, nome)
        return EncryptedFile.Builder(
            file,
            requireContext(),
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB)
            .build()
    }

    fun clickLerTexto(arquivo: String): String {
        val encryptedIn: FileInputStream =
            getEncFile("${arquivo}.txt").openFileInput()
        val br = BufferedReader(InputStreamReader(encryptedIn))
        var stringToPrint = ""
        stringToPrint = br.readText()

        encryptedIn.close()
        return stringToPrint
    }

    fun clickLerImagem(arquivo: String): Bitmap? {
        val encryptedIn: FileInputStream = getEncFile("${arquivo}.fig").openFileInput()
        val imageBitmap = BitmapFactory.decodeStream(encryptedIn)

        encryptedIn.close()
        return imageBitmap
    }

}