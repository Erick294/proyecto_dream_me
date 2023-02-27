package com.jke.dreamme.model.entities.api.usuario

data class StableAPI(
    val key: String = "irofPy52RKVGaX2qP47gpmYLp7WZlYPYY0ISSjBXdTynWSalRaJBxZmeAbix",
    var prompt: String,
    val negative_prompt: String = "",
    val width: Int = 512,
    val height: Int = 512,
    val samples: Int = 1,
    val num_inference_steps: Int = 50,
    val safety_checker: String = "no",
    val enhance_prompt: String = "yes",
    val guidance_scale: Double = 7.5,
    val strength: Double = 0.7,
    val seed: String = "",
    val webhook: String = "",
    val track_id: String = ""
)
