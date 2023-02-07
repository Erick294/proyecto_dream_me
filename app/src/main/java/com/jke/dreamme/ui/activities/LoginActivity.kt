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

    private fun initLogin(){
        val txtEmail = binding.login_txt_email
        binding.buttonLogin.setOnClickListener {
            validarEmail(txtEmail.text.toString())
        }
    }

    private fun validarEmail(email:String) {

	    lifecycleScope.launch {
            val usuario = UsuarioUC().getUsuario(email)
            if (usuario?.status == "active") {
                var intent = Intent(this@LoginActivity, PrincipalActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(
                    txt, "Email incorrecto o inactivo",
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