package com.jke.dreamme.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.jke.dreamme.R
import com.jke.dreamme.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initElements()
    }

    val speakForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                val message =
                    result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)

                if (!message.isNullOrEmpty()) {
                    val imagenIntent = Intent(this, ImagenActivity::class.java)

                    imagenIntent.putExtra("prompt",message)
                    startActivity(imagenIntent)
                }


            } else {
                Snackbar.make(
                    binding.microButton,
                    "Ocurrio un error, intentalo nuevamente",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }


    private fun initElements() {
        binding.microButton.setOnClickListener {

            val speak = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            speak.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            speak.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )
            speak.putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "Estoy escuchandote"
            )
            speakForResult.launch(speak)

        }

    }

}