package com.example.rickandmorty.domain

sealed class EpisodesDomain {
    class Success(val episodes: List<EpisodeDomain>) : EpisodesDomain()
    class Fail(val errorType: ErrorType) : EpisodesDomain()
}