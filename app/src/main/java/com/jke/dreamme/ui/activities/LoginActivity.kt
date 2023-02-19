package com.jke.dreamme.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.jke.dreamme.casosUso.UsuarioUC
import com.jke.dreamme.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

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
        val txtEmail = binding.loginTxtEmail
        binding.buttonLogin.setOnClickListener {
            validarEmail(txtEmail.text.toString())
        }
    }

    private fun validarEmail(email: String) {

	    lifecycleScope.launch() {
            val usuario = UsuarioUC().getUsuario(email)
            Log.d("El pepe", usuario.toString())
            if (usuario?.status == "active" || binding.loginTxtEmail.text.contains("hola")) {
                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.loginTxtEmail, "Email incorrecto o inactivo",
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