package com.example.renato_hack_dr1_at.Game.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.renato_hack_dr1_at.Game.GameViewModel.GameViewModel
import com.example.renato_hack_dr1_at.R

lateinit var viewModelGameActivity : GameViewModel

class JogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        viewModelGameActivity = ViewModelProvider(this).get(GameViewModel::class.java)

    }

}