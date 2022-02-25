package com.example.rickandmorty.presentation.characterInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.Interactor

class CharacterInfoViewModelFactory(
    private val interactor: Interactor,
    private val mapper: DomainToUiFullInfoMapper
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        CharacterInfoViewModel::class.java -> CharacterInfoViewModel(interactor, mapper)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}