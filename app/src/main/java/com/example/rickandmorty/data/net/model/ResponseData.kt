package com.example.rickandmorty.data.net.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val info: Info,
    val results: List<Results>
)