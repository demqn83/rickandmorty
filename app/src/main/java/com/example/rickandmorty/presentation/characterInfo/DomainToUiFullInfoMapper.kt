package com.example.rickandmorty.presentation.characterInfo

import com.example.rickandmorty.domain.CharacterFullInfoDomain

interface DomainToUiFullInfoMapper {
    fun map(characterFullInfoDomain: CharacterFullInfoDomain): CharacterFullInfoUI
}