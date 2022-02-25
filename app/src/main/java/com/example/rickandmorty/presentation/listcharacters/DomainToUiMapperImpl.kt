package com.example.rickandmorty.presentation.listcharacters

import com.example.rickandmorty.domain.CharactersDomain

class DomainToUiMapperImpl : DomainToUiMapper {
    override fun map(charactersDomain: CharactersDomain): List<CharacterUI> {
        return when (charactersDomain) {
            is CharactersDomain.Success -> {
                val results = mutableListOf<CharacterUI>()
                charactersDomain.characters.forEach {
                    results.add(CharacterUI.Success(it.id, it.name, it.photo))
                }
                return results
            }
            is CharactersDomain.Fail -> listOf(CharacterUI.Fail)
        }
    }
}
