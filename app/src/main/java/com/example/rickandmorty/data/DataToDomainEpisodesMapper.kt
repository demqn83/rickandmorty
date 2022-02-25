package com.example.rickandmorty.data

import com.example.rickandmorty.domain.EpisodesDomain

interface DataToDomainEpisodesMapper {
    fun map(episodesData: EpisodesData): EpisodesDomain
}