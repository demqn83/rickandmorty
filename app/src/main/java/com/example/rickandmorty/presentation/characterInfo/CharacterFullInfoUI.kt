package com.example.rickandmorty.presentation.characterInfo

sealed class CharacterFullInfoUI {
    class Success(
        val id: Int,
        val image: String,
        val name: String,
        val location: String,
        val status: String,
        val species: String
    ) : CharacterFullInfoUI()

    object Fail : CharacterFullInfoUI()
}