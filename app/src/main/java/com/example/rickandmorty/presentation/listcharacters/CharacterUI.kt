package com.example.rickandmorty.presentation.listcharacters

sealed class CharacterUI {
    class Success(val id: Int, val name: String, val photo: String) : CharacterUI()
    object Fail : CharacterUI()
    object Progress : CharacterUI()
}