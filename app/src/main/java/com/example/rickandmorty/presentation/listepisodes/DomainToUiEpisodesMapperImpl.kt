package com.example.rickandmorty.presentation.listepisodes

import com.example.rickandmorty.domain.EpisodesDomain

class DomainToUiEpisodesMapperImpl : DomainToUiEpisodesMapper {
    override fun map(episodes: EpisodesDomain): List<EpisodeUI> {
        return when (episodes) {
            is EpisodesDomain.Success -> {
                val results = mutableListOf<EpisodeUI>()
                episodes.episodes.forEach {
                    results.add(EpisodeUI.Success(it.name, it.air_date))
                }
                return results
            }
            is EpisodesDomain.Fail -> listOf(EpisodeUI.Fail)
        }
    }
}