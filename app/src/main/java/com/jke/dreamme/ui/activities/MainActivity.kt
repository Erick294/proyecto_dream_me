package com.jke.dreamme.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
        initAnim()
        initElements()
    }

    val speakForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                val message =
                    result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)

                if (!message.isNullOrEmpty()) {
                    val principalIntent = Intent(this, ImagenActivity::class.java)

                    principalIntent.putExtra("prompt",message)
                    startActivity(principalIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
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
        intent.extras?.let {
            val nombre = it.getString("nombre").toString()
            binding.textBienvenidaMain.text = "Bienvenid@\n" + nombre
        }

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

    private fun initAnim(){
        binding.textBienvenidaMain.startAnimation(animationTranslate())
        binding.textInstruccionMain.startAnimation(animationScale())
        binding.textInstruccion2Main.startAnimation(animationScale())

    }


    private fun animationTranslate(): Animation{
        val animation: Animation;

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_text_translate)
        return animation
    }

    private fun animationScale(): Animation{
        val animation: Animation;

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_text_scale)
        return animation
    }

}