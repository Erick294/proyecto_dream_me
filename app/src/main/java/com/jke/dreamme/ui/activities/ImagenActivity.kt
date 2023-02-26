package com.jke.dreamme.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.jke.dreamme.casosUso.StableUC
import com.jke.dreamme.casosUso.UsuarioUC
import com.jke.dreamme.databinding.ActivityImagenBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ImagenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImagenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtenerImagen()
    }

    private fun obtenerImagen() {
        var prompt: String = ""

        intent.extras?.let {
            prompt = it.getString("prompt").toString()
        }

        lifecycleScope.launch() {
            var respuesta = StableUC().getImagen(prompt)
            Log.d("Data", respuesta.toString())
            if (respuesta?.status == "success") {
                val imagen = respuesta.output[0]
                Picasso.get().load(imagen).into(binding.imagenGenerada)
                binding.textPrompt.text = prompt
            }

            if(respuesta?.status == "processing"){
                var tiempo = respuesta.generationTime
                Log.d("Tiempo", tiempo.toString())
                Handler().postDelayed(Runnable { //This method will be executed once the timer is over
                    // Start your app main activity
                    // close this activity
                    Log.d("Esperando", respuesta.toString())
                    finish()
                }, tiempo.toLong())

                val respuestaFetch = StableUC().getFetch(respuesta.id)
                val imagen = respuestaFetch?.output?.get(0)
                Picasso.get().load(imagen).into(binding.imagenGenerada)
            }
        }
    }
}