package com.example.rickandmorty.data

sealed class CharactersData {
    class Success(val characters: List<CharacterData>) : CharactersData()
    class Fail(val e: Exception) : CharactersData()
}
