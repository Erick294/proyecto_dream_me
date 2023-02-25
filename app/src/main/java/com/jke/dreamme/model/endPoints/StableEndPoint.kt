package com.jke.dreamme.model.endPoints

import com.jke.dreamme.model.entities.api.usuario.StableBody
import com.jke.dreamme.model.entities.api.usuario.StableHeader
import com.jke.dreamme.model.entities.api.usuario.StableResponse
import retrofit2.Response
import retrofit2.http.*

interface StableEndPoint {
    @Headers("Content-Type: application/json", "Authorization: Token 6cd04921633e6557ddb7cbe6d240fa79d1ee4996")
    @POST("predictions")
    suspend fun generarImagen(@Body tokenStable: StableBody): Response<StableResponse>;

    @Headers("Content-Type: application/json", "Authorization: Token 6cd04921633e6557ddb7cbe6d240fa79d1ee4996")
    @GET("predictions/{id}")
    suspend fun getImagenGenerada(@Path("id") id: String, @Header("Authorization") token:String): Response<StableResponse>;
}