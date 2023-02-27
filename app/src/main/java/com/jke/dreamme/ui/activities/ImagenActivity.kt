package com.jke.dreamme.ui.activities

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.jke.dreamme.R
import com.jke.dreamme.casosUso.StableUC
import com.jke.dreamme.databinding.ActivityImagenBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*


class ImagenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImagenBinding
    var imagen: String = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fade()

        initAnim()
        obtenerImagen()
    }

    private fun obtenerImagen() {
        var prompt: String = ""

        intent.extras?.let {
            prompt = it.getString("prompt").toString()
        }

        lifecycleScope.launch() {
            val respuesta = StableUC().getImagen(prompt)
            Log.d("Data", respuesta.toString())

            if (respuesta?.status == "success") {
                imagen = respuesta.output[0]
                Picasso.get().load(imagen).into(binding.imagenGenerada)
                binding.textPrompt.text = prompt
            }

            if(respuesta?.status == "processing"){
                val tiempo = respuesta.generationTime

                Log.d("Tiempo", tiempo.toString())
                
                val respuestaFetch = StableUC().getFetch(respuesta.id)
                imagen = respuestaFetch?.output?.get(0).toString()

                    Handler().postDelayed(Runnable { //This method will be executed once the timer is over
                        // Start your app main activity
                        // close this activity
                        Log.d("Esperando", respuesta.toString())        
                        Picasso.get().load(imagen).into(binding.imagenGenerada)
                        finish()
                    }, 3500)
                }
            }
        }

    private fun fade(){
        binding.imagenGenerada.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                .alpha(1f)
                .setDuration(4000)
                .setListener(null)
        }
        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        binding.loadingSpinner.animate()
            .alpha(0f)
            .setDuration(7000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.loadingSpinner.visibility = View.GONE
                }
            })
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(WRITE_EXTERNAL_STORAGE, false) -> {

            }
            else -> {

            }
        }
    }

    /*
    private fun saveToGallery() {
        val content = createContent()
        val uri = save(content)
        clearContents(content, uri)
    }

    private fun createContent(): ContentValues {
        val fileName = imagen
        val fileType = "image/jpg"
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, fileType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    private fun save(content: ContentValues): Uri {
        var outputStream: OutputStream?
        var uri: Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use { output ->
            getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, output)
        }
        return uri!!
    }

    private fun clearContents(content: ContentValues, uri: Uri) {
        content.clear()
        content.put(MediaStore.MediaColumns.IS_PENDING,0)
        contentResolver.update(uri,content,null,null)
    }

    private fun getBitmap(): Bitmap {
        return BitmapFactory.decodeFile(imagen)
    }
    */

    private fun initAnim(){
        binding.textPrompt.startAnimation(animationTranslate())
        binding.imagenGenerada.startAnimation(animationScale())
    }

    private fun animationTranslate(): Animation {
        val animation: Animation;

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_text_translate)
        return animation
    }

    private fun animationScale(): Animation {
        val animation: Animation;

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_text_scale)
        return animation
    }

}
