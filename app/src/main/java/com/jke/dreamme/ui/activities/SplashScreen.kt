package com.jke.dreamme.ui.activities

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jke.dreamme.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            // Start your app main activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // close this activity
            finish()
        }, 2000)
    }
}