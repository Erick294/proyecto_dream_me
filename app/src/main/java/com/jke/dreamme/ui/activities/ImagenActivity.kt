package com.jke.dreamme.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.jke.dreamme.casosUso.StableUC
import com.jke.dreamme.databinding.ActivityImagenBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImagenBinding
    private var idn: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        generarImagen("jashjabsahj")
        obtenerImagen(idn)
    }

    private fun generarImagen(prom: String){

        lifecycleScope.launch(){
            var respuesta = StableUC().generarImagen(prom)
            Log.d("El pepe", respuesta.toString())
            if (respuesta != null) {
                idn = respuesta
            }
        }
    }

    private fun obtenerImagen(id: String) {

        lifecycleScope.launch(Dispatchers.Main) {
            var respuesta = StableUC().obtenerImagen(id)
            Log.d("El pepe", respuesta.toString())

            if(respuesta?.status == "processing"){
                Handler().postDelayed(Runnable { //This method will be executed once the timer is over
                    // Start your app main activity
                    // close this activity
                    Log.d("Esperando", respuesta.toString())

                }, 100000)

            if (respuesta?.status == "succeeded") {
                val imagen = respuesta.output
                Picasso.get().load(imagen.toString()).into(binding.imagenGenerada)
            }
                val respuestaFetch = StableUC().obtenerImagen(respuesta.id)
                val imagen = respuestaFetch?.output
                Picasso.get().load(imagen.toString()).into(binding.imagenGenerada)
            }

            if(respuesta?.status == "failed"){
                Snackbar.make(
                    binding.imagenGenerada, "Error",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
