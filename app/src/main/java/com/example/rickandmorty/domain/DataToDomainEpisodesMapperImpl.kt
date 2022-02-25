package com.example.rickandmorty.domain

import com.example.rickandmorty.data.DataToDomainEpisodesMapper
import com.example.rickandmorty.data.EpisodesData
import retrofit2.HttpException
import java.net.UnknownHostException

class DataToDomainEpisodesMapperImpl : DataToDomainEpisodesMapper {
    override fun map(episodesData: EpisodesData): EpisodesDomain {
        return when (episodesData) {
            is EpisodesData.Success -> {
                val result = mutableListOf<EpisodeDomain>()
                episodesData.episodes.forEach {
                    result.add(EpisodeDomain(it.name, it.air_date))
                }
                EpisodesDomain.Success(result)
            }
            is EpisodesData.Fail -> EpisodesDomain.Fail(
                when (episodesData.e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            )
        }

    }
}