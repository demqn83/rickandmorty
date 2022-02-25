package com.example.rickandmorty.presentation.characterInfo

import com.example.rickandmorty.domain.CharacterFullInfoDomain

class DomainToUiFullInfoMapperImpl : DomainToUiFullInfoMapper {
    override fun map(characterFullInfoDomain: CharacterFullInfoDomain): CharacterFullInfoUI {
        return when (characterFullInfoDomain) {
            is CharacterFullInfoDomain.Success -> CharacterFullInfoUI.Success(
                characterFullInfoDomain.id,
                characterFullInfoDomain.image,
                characterFullInfoDomain.name,
                characterFullInfoDomain.location,
                characterFullInfoDomain.status,
                characterFullInfoDomain.species
            )
            is CharacterFullInfoDomain.Fail -> CharacterFullInfoUI.Fail
        }
    }
}