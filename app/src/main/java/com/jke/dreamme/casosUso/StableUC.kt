package com.jke.dreamme.casosUso

import android.util.Log
import com.jke.dreamme.model.endPoints.StableEndPoint
import com.jke.dreamme.model.entities.api.usuario.RespuestaStable
import com.jke.dreamme.model.entities.api.usuario.StableAPI
import com.jke.dreamme.model.entities.api.usuario.Usuario
import com.jke.dreamme.model.repositories.APIRepository

class StableUC {

    suspend fun getImagen(prompt:String): RespuestaStable? {
        val c = try {
            val service = APIRepository().buildStableService(StableEndPoint::class.java)
            val response = service.getImagen(StableAPI(prompt = prompt))
            Log.d("Imagen", response.toString())
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
        return c
    }

    suspend fun getFetch(id:Long): RespuestaStable?{
        val c = try {
            val service = APIRepository().buildStableService(StableEndPoint::class.java)
            val response = service.getFetch(StableAPI(prompt = ""), id)
            Log.d("Fetch", response.toString())
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
        return c
    }

}