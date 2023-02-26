package com.jke.dreamme.model.entities.api.usuario

data class MetaStable (

    val h: Long,
    val w: Long,
    val enableAttentionSlicing: String,
    val filePrefix: String,
    val guidanceScale: Double,
    val model: String,
    val nSamples: Long,
    val negativePrompt: String,
    val outdir: String,
    val prompt: String,
    val revision: String,
    val safetychecker: String,
    val seed: Long,
    val steps: Long,
    val vae: String
)