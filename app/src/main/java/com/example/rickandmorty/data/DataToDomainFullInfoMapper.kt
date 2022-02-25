package com.example.rickandmorty.data

import com.example.rickandmorty.domain.CharacterFullInfoDomain

interface DataToDomainFullInfoMapper {
    fun map(characterFullInfoData: CharacterFullInfoData): CharacterFullInfoDomain
}