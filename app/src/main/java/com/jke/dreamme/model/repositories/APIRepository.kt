package com.jke.dreamme.model.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRepository {
    private fun getRetrofitBuilder(base: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> buildUsuarioService(servicio: Class<T>): T {
        val builder = getRetrofitBuilder("https://gorest.co.in/public/v2/")
        return builder.create(servicio)
    }

}