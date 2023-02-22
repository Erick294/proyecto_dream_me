package com.jke.dreamme.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
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
        checkBiometric()
    }

    private fun runBiometric(){

        val ejecutor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this,ejecutor,object :BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Snackbar.make(binding.imageView3,"Error en lectura de la huella", Snackbar.LENGTH_LONG).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Snackbar.make(binding.imageView3,"Huella no encontrada", Snackbar.LENGTH_LONG).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                val intent = Intent(this@LoginActivity,PrincipalActivity::class.java)
                startActivity(intent)
            }
        })

        val biometricPromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Auntenticacin de la huella")
            .setSubtitle("Ingrese su huella para autenticarse")
            .setNegativeButtonText("Uasar el usuario y contraseña")
            .build()

        binding.finger.setOnClickListener {
            biometricPrompt.authenticate(biometricPromptInfo)
        }
    }


    private fun checkBiometric() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                binding.finger.visibility = View.VISIBLE
                runBiometric()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                binding.finger.visibility = View.GONE
                Snackbar.make(binding.imageView3,"No existe sensor en el dispositivo", Snackbar.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Snackbar.make(binding.imageView3,"Existe en error en el biometrico", Snackbar.LENGTH_LONG).show()

            binding.finger.visibility = View.GONE
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                val intentEnrol = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                 BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            }
        }
    }
    }




     private fun initLogin(){
        /* val txtEmail = binding.loginTxtEmail
         binding.buttonLogin.setOnClickListener {
             validarEmail(txtEmail.text.toString())
         }*/

     }

     private fun validarEmail(email: String) {

         lifecycleScope.launch() {
             val usuario = UsuarioUC().getUsuario(email)
             Log.d("El pepe", usuario.toString())
            /* if (usuario?.status == "active" || binding.loginTxtEmail.text.contains("hola")) {
                 var intent = Intent(this@LoginActivity, MainActivity::class.java)
                 startActivity(intent)
             } else {
                 Snackbar.make(
                     binding.loginTxtEmail, "Email incorrecto o inactivo",
                     Snackbar.LENGTH_SHORT
                 ).show()
             }*/
         }



     }

     private fun initRegister() {
        /* binding.registerButton .setOnClickListener {
             var intent = Intent(this, RegisterActivity::class.java)
             startActivity(intent)
         }*/
     }
}