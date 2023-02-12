package com.jke.dreamme.model.endPoints

import com.jke.dreamme.model.entities.api.usuario.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioEndPoint {
    @GET("users/{numero}")
    suspend fun getUsuario(@Path("numero") numero: Int): Response<Usuario>;

    @GET("users")
    suspend fun getListUsuario(): Response<List<Usuario>>;
}