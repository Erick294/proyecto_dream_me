package com.jke.dreamme.casosUso

import android.util.Log
import com.jke.dreamme.model.endPoints.UsuarioEndPoint
import com.jke.dreamme.model.entities.api.usuario.Usuario
import com.jke.dreamme.model.repositories.APIRepository

class UsuarioUC {
    suspend fun getUsuario(id: String): Usuario? {
        var data: Usuario? = null
        var idUser: Int = 0
        try {
            val servicio = APIRepository().buildUsuarioService(UsuarioEndPoint::class.java)
            var lista = servicio.getListUsuario()

            for(i in (lista.body())!!){
                if(i.id.toString() == id){
                   idUser = i.id
                }else{
                    Log.d("ERRORRRRRRR",i.email)
                }
            }


            val response = servicio.getUsuario(idUser)
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