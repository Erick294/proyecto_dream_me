package com.jke.dreamme.casosUso

import android.util.Log
import com.jke.dreamme.model.endPoints.UsuarioEndPoint
import com.jke.dreamme.model.entities.api.usuario.Usuario
import com.jke.dreamme.model.repositories.APIRepository

class UsuarioUC {
    suspend fun getUsuario(usuario: String): Usuario? {
        var data: Usuario? = null
        try {
            val servicio = APIRepository().buildUserService(UsuarioEndPoint::class.java)
            val response = servicio.getUser(usuario)
            if (response.isSuccessful) {
                data = response.body()!!
            } else {
                throw Exception("No se realizo la conexión")
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return data
    }
}