package com.example.rickandmorty.domain

sealed class CharacterFullInfoDomain {
    class Success(
        val id: Int,
        val image: String,
        val name: String,
        val location: String,
        val status: String,
        val species: String
    ) : CharacterFullInfoDomain()

    class Fail(val e: ErrorType) : CharacterFullInfoDomain()
}