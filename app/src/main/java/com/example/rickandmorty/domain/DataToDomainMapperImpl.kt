package com.example.rickandmorty.domain

import com.example.rickandmorty.data.CharactersData
import com.example.rickandmorty.data.DataToDomainMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class DataToDomainMapperImpl : DataToDomainMapper {
    override fun map(charactersData: CharactersData): CharactersDomain {
        return when (charactersData) {
            is CharactersData.Success -> {
                val results = mutableListOf<CharacterDomain>()
                charactersData.characters.forEach {
                    results.add(CharacterDomain(it.id, it.name, it.photo))
                }
                CharactersDomain.Success(results)
            }
            is CharactersData.Fail -> CharactersDomain.Fail(
                when (charactersData.e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            )
        }

    }
}