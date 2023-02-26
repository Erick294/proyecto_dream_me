package com.jke.dreamme.model.endPoints

import com.jke.dreamme.model.entities.api.usuario.RespuestaStable
import com.jke.dreamme.model.entities.api.usuario.StableAPI

import retrofit2.Response
import retrofit2.http.*

interface StableEndPoint {

    @Headers("Content-Type: application/json")
    @POST("text2img")
    suspend fun getImagen(@Body cuerpo: StableAPI): Response<RespuestaStable>;

    @POST("fetch/{id}")
    suspend fun getFetch(@Body cuerpo: StableAPI, @Path("id") id:Long): Response<RespuestaStable>

}