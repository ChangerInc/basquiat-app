package com.changer.basquiat.domain.model

data class ConversionResponse(
    val result: Result
)
data class Result(
    val error: Map<String, Any>,
    val output: Output,
    val warning: Map<String, Any>
)
data class Output(
    val connector: String,
    val name: String,
    val size: Int,
    val expires_at: String
)