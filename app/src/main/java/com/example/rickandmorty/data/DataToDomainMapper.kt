package com.example.rickandmorty.data

import com.example.rickandmorty.domain.CharactersDomain

interface DataToDomainMapper {
    fun map(charactersData: CharactersData): CharactersDomain
}