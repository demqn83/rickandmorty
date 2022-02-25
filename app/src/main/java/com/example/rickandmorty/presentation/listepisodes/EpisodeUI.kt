package com.example.rickandmorty.presentation.listepisodes

sealed class EpisodeUI {
    class Success(val name: String, val air_date: String) : EpisodeUI()
    object Fail : EpisodeUI()
    object Progress : EpisodeUI()
}