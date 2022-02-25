package com.example.rickandmorty.presentation.listcharacters

import com.example.rickandmorty.domain.CharactersDomain

interface DomainToUiMapper {
    fun map(charactersDomain: CharactersDomain): List<CharacterUI>
}