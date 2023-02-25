package com.jke.dreamme.model.entities.api.usuario

data class StableResponse(
    val completed_at: Any,
    val created_at: String,
    val error: Any,
    val id: String,
    val input: InputX,
    val logs: Any,
    val metrics: Metrics,
    val output: Any,
    val started_at: Any,
    val status: String,
    val urls: Urls,
    val version: String
)