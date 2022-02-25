package com.example.rickandmorty.presentation.listepisodes

import com.example.rickandmorty.domain.EpisodesDomain

interface DomainToUiEpisodesMapper {
    fun map(episodes: EpisodesDomain) : List<EpisodeUI>
}