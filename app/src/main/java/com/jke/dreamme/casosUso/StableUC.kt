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
                Log.d("Success", response.toString())
                response.body()!!
                id = response.body()!!.id
            } else {
                Log.d("Error", response.toString())
                throw Exception("No se realizo la conexión")
            }
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
        }
        return id
    }

    suspend fun obtenerImagen(id: String): StableResponse{
        val data: StableResponse;
        val c = try {
            val service = APIRepository().buildStableService(StableEndPoint::class.java)
            val response = service.getImagenGenerada(id, "Token 6cd04921633e6557ddb7cbe6d240fa79d1ee4996")
            Log.d("Fetch", response.toString())
            if (response.isSuccessful) {
                data = response.body()!!
                Log.d("Success", response.toString())
            } else {
                Log.d("Error", response.toString())
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            throw Exception(e.toString())
        }
        return data
    }

}