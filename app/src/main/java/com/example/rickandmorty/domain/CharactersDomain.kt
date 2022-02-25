package com.example.rickandmorty.domain

sealed class CharactersDomain {
    class Success(val characters: List<CharacterDomain>) : CharactersDomain()
    class Fail(private val errorType: ErrorType) : CharactersDomain()
}