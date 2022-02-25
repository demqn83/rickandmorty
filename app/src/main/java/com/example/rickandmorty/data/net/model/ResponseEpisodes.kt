package com.example.rickandmorty.data.net.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseEpisodes(
    val info: Info,
    val results: List<ResultsEpisode>
)