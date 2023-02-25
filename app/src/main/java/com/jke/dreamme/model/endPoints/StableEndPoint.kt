package com.jke.dreamme.model.endPoints

import com.jke.dreamme.model.entities.api.usuario.StableBody
import com.jke.dreamme.model.entities.api.usuario.StableResponse
import retrofit2.Response
import retrofit2.http.*

interface StableEndPoint {
    @Headers("Content-Type: application/json")
    @POST("oauth2/token")
    suspend fun generarImagen(@Body tokenStable: StableBody): Response<StableResponse>;

    @GET("predictions/{id}")
    suspend fun getImagenGenerada(@Path("id") id: String): Response<StableResponse>;
}