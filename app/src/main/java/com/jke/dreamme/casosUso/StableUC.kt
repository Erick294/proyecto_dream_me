package com.jke.dreamme.casosUso

import android.util.Log
import com.jke.dreamme.model.endPoints.StableEndPoint
import com.jke.dreamme.model.entities.api.usuario.Input
import com.jke.dreamme.model.entities.api.usuario.StableBody
import com.jke.dreamme.model.entities.api.usuario.StableResponse
import com.jke.dreamme.model.repositories.APIRepository
import com.squareup.picasso.Picasso

class StableUC {

    val servicio = APIRepository().buildStableService(StableEndPoint::class.java)

    suspend fun generarImagen(prom: String): String{
        var id: String = ""
        try {
            val response = servicio.generarImagen(StableBody(input = Input(prompt = prom)))

            if (response.isSuccessful) {
                response.body()!!
                id = response.body()!!.id
            } else {
                throw Exception("No se realizo la conexión")
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return id
    }

    suspend fun obtenerImagen(id: String): StableResponse{
        val c = try {
            val service = APIRepository().buildStableService(StableEndPoint::class.java)
            val response = service.getImagenGenerada(id)
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