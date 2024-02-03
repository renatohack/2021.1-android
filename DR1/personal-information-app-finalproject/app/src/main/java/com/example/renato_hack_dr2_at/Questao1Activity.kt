package com.example.renato_hack_dr2_at

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_questao1.*

class Questao1Activity : AppCompatActivity() {

    var counter : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questao1)

        setColors()

        button_questao2.setOnClickListener {
            irParaQuestao2()
        }

        button_altocontraste.setOnClickListener {
            changeContrastMode()
        }
    }

    private fun irParaQuestao2() {
        val intent = Intent(this, Questao2Activity::class.java)
        startActivity(intent)
    }

    fun changeContrastMode(){

        if (counter == 0){
            background.setBackgroundColor(Color.DKGRAY)

            header_TextView.setTextColor(Color.YELLOW)
            header_TextView.setBackgroundColor(Color.BLACK)
            subheader_textView.setTextColor(Color.YELLOW)
            subheader_textView.setBackgroundColor(Color.BLACK)

            nome_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.YELLOW)
            nome_editText.setBackgroundColor(Color.BLACK)
            nome_editText.setTextColor(Color.YELLOW)

            endereco_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.YELLOW)
            endereco_editText.setBackgroundColor(Color.BLACK)
            endereco_editText.setTextColor(Color.YELLOW)

            estado_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.YELLOW)
            estado_editText.setBackgroundColor(Color.BLACK)
            estado_editText.setTextColor(Color.YELLOW)

            cidade_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.YELLOW)
            cidade_editText.setBackgroundColor(Color.BLACK)
            cidade_editText.setTextColor(Color.YELLOW)

            telefone_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.YELLOW)
            telefone_editText.setBackgroundColor(Color.BLACK)
            telefone_editText.setTextColor(Color.YELLOW)

            email_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.YELLOW)
            email_editText.setBackgroundColor(Color.BLACK)
            email_editText.setTextColor(Color.YELLOW)

            button_altocontraste.setBackgroundColor(Color.CYAN)
            button_altocontraste.setTextColor(Color.BLACK)
            button_questao2.setBackgroundColor(Color.CYAN)
            button_questao2.setTextColor(Color.BLACK)

            counter = 1
        }

        else if (counter == 1){
            background.setBackgroundColor(Color.WHITE)

            header_TextView.setTextColor(Color.BLACK)
            header_TextView.setBackgroundColor(Color.TRANSPARENT)
            subheader_textView.setTextColor(Color.BLACK)
            subheader_textView.setBackgroundColor(Color.TRANSPARENT)

            nome_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
            nome_editText.setBackgroundColor(Color.LTGRAY)
            nome_editText.setTextColor(Color.BLACK)

            endereco_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
            endereco_editText.setBackgroundColor(Color.LTGRAY)
            endereco_editText.setTextColor(Color.BLACK)

            estado_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
            estado_editText.setBackgroundColor(Color.LTGRAY)
            estado_editText.setTextColor(Color.BLACK)

            cidade_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
            cidade_editText.setBackgroundColor(Color.LTGRAY)
            cidade_editText.setTextColor(Color.BLACK)

            telefone_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
            telefone_editText.setBackgroundColor(Color.LTGRAY)
            telefone_editText.setTextColor(Color.BLACK)

            email_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
            email_editText.setBackgroundColor(Color.LTGRAY)
            email_editText.setTextColor(Color.BLACK)

            button_altocontraste.setBackgroundColor(Color.RED)
            button_altocontraste.setTextColor(Color.WHITE)
            button_questao2.setBackgroundColor(Color.RED)
            button_questao2.setTextColor(Color.WHITE)

            counter = 0
        }

    }

    fun setColors(){
        background.setBackgroundColor(Color.WHITE)

        header_TextView.setTextColor(Color.BLACK)
        header_TextView.setBackgroundColor(Color.TRANSPARENT)
        subheader_textView.setTextColor(Color.BLACK)
        subheader_textView.setBackgroundColor(Color.TRANSPARENT)

        nome_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
        nome_editText.setBackgroundColor(Color.LTGRAY)
        nome_editText.setTextColor(Color.BLACK)

        endereco_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
        endereco_editText.setBackgroundColor(Color.LTGRAY)
        endereco_editText.setTextColor(Color.BLACK)

        estado_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
        estado_editText.setBackgroundColor(Color.LTGRAY)
        estado_editText.setTextColor(Color.BLACK)

        cidade_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
        cidade_editText.setBackgroundColor(Color.LTGRAY)
        cidade_editText.setTextColor(Color.BLACK)

        telefone_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
        telefone_editText.setBackgroundColor(Color.LTGRAY)
        telefone_editText.setTextColor(Color.BLACK)

        email_textInputLayout.defaultHintTextColor = ColorStateList.valueOf(Color.BLACK)
        email_editText.setBackgroundColor(Color.LTGRAY)
        email_editText.setTextColor(Color.BLACK)

        button_altocontraste.setBackgroundColor(Color.RED)
        button_altocontraste.setTextColor(Color.WHITE)
        button_questao2.setBackgroundColor(Color.RED)
        button_questao2.setTextColor(Color.WHITE)
    }
}