package com.zero.dream_me

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.zero.dream_me.databinding.ActivityBienvenidaBinding

class Bienvenida : AppCompatActivity() {

    private lateinit var binding: ActivityBienvenidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val video = binding.videoLogo as VideoView
        val path = "android.resource://" + packageName + "/" + R.raw.logo_video
        video.setVideoURI(Uri.parse(path))

        video.start()
    }
}