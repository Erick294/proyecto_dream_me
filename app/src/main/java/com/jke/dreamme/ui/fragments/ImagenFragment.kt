package com.jke.dreamme.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.jke.dreamme.casosUso.StableUC
import com.jke.dreamme.databinding.FragmentImagenBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagenFragment : Fragment() {
    private lateinit var binding: FragmentImagenBinding
    private lateinit var idn: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        binding = FragmentImagenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
        generarImagen("jashjabsahj")
    }

    private fun init() {
        lifecycleScope.launch(Dispatchers.Main) {

        }
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

        lifecycleScope.launch() {
            var respuesta = StableUC().obtenerImagen(id)
            Log.d("El pepe", respuesta.toString())
            if (respuesta?.status == "succeeded") {
                val imagen = respuesta.output
                Picasso.get().load(imagen.toString()).into(binding.imageView)
            }

            if(respuesta?.status == "processing"){
                var tiempo = respuesta.metrics
                Handler().postDelayed(Runnable { //This method will be executed once the timer is over
                    // Start your app main activity
                    // close this activity
                    Log.d("Esperando", respuesta.toString())

                }, 40000)
                val respuestaFetch = StableUC().obtenerImagen(respuesta.id)
                val imagen = respuestaFetch?.output
                Picasso.get().load(imagen.toString()).into(binding.imageView)
            }else{
                Snackbar.make(
                    binding.imageView, "Error",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}