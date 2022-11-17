package com.course.criptomonedas.data.models

data class ResponseBooks(
    val payload: List<ModelBook>,
    val success: Boolean
)