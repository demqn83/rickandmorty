package com.example.rickandmorty.data

import java.lang.Exception

sealed class CharacterFullInfoData {
    class Success(
        val id: Int,
        val image: String,
        val name: String,
        val location: String,
        val status: String,
        val species: String
    ) : CharacterFullInfoData()

    class Fail(val e: Exception) : CharacterFullInfoData()
}