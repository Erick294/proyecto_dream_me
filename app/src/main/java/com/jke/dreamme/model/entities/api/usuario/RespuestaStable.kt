package com.jke.dreamme.model.entities.api.usuario

data class RespuestaStable(
    val status: String,
    val generationTime: Double,
    val eta: Double,
    val id: Long,
    val output: List<String>,
    val meta: MetaStable
)
