package com.example.rickandmorty.data

import java.lang.Exception

sealed class EpisodesData {
    class Success(val episodes: List<EpisodeData>) : EpisodesData()
    class Fail(val e: Exception) : EpisodesData()
}