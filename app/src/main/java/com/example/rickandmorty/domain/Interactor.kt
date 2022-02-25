package com.example.rickandmorty.domain

import com.example.rickandmorty.data.DataToDomainEpisodesMapper
import com.example.rickandmorty.data.DataToDomainFullInfoMapper
import com.example.rickandmorty.data.DataToDomainMapper
import com.example.rickandmorty.data.Repository

class Interactor(
    private val repository: Repository,
    private val mapper: DataToDomainMapper,
    private val mapperFullInfo: DataToDomainFullInfoMapper,
    private val mapperEpisodes: DataToDomainEpisodesMapper,
) {
    suspend fun getAllCharacters(): CharactersDomain {
        return mapper.map(repository.getAllCharacters())
    }

    suspend fun getCharacter(id: Int): CharacterFullInfoDomain {
        return mapperFullInfo.map(repository.getCharacter(id))
    }

    suspend fun getAllEpisodes(id: Int): EpisodesDomain {
        return mapperEpisodes.map(repository.getAllEpisodes(id))
    }
}