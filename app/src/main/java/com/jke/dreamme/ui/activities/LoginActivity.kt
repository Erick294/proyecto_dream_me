package com.jke.dreamme.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.jke.dreamme.databinding.ActivityLoginBinding
import com.jke.dreamme.databinding.ActivitySplashScreenBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLogin()
        initRegister()
    }

    private fun initLogin() {
        val txt = binding.loginTxtUser
        binding.buttonLogin.setOnClickListener {
            val txtUser = binding.loginTxtUser.text.toString()
            val txtPass = binding.loginTxtPass.text.toString()

            if (txtUser == ("admin") && txtPass == "admin") {
                var intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(
                    txt, "Nombre de usuario o contraseña incorrectos",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initRegister() {
        binding.registerButton .setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}