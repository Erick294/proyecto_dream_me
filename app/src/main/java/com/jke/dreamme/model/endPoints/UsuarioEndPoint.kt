package com.jke.dreamme.model.endPoints

package com.jke.dreamme.model.entities.api.usuario.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserEndPoint {
    @GET("users/{numero}")
    suspend fun getUsuario(@Path("numero") numero: String): Response<Usuario>;
}