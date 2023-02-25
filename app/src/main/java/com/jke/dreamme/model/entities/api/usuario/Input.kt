package com.jke.dreamme.model.entities.api.usuario

data class Input(
    val text: String = "",
    val prompt: String,
    val image_dimensions: String = "768x768",
    val negative_prompt: String= "",
    val num_outputs: Int = 1,
    val num_inference_steps: Int = 50,
    val guidance_scale: Double = 7.5,
    val scheduler: String = "DPMSolverMultistep",
    val seed: String="",
)