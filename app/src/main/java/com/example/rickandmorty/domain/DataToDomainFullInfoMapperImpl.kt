package com.example.rickandmorty.domain

import com.example.rickandmorty.data.CharacterFullInfoData
import com.example.rickandmorty.data.DataToDomainFullInfoMapper
import retrofit2.HttpException
import java.net.UnknownHostException

class DataToDomainFullInfoMapperImpl : DataToDomainFullInfoMapper {
    override fun map(characterFullInfoData: CharacterFullInfoData): CharacterFullInfoDomain {

        return when (characterFullInfoData) {
            is CharacterFullInfoData.Success -> CharacterFullInfoDomain.Success(
                characterFullInfoData.id,
                characterFullInfoData.image,
                characterFullInfoData.name,
                characterFullInfoData.location,
                characterFullInfoData.status,
                characterFullInfoData.species
            )
            is CharacterFullInfoData.Fail -> CharacterFullInfoDomain.Fail(
                when (characterFullInfoData.e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            )
        }

    }
}