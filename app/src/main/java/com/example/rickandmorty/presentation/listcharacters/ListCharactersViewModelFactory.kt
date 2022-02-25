package com.example.rickandmorty.presentation.listcharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.Interactor

class ListCharactersViewModelFactory(
    private val interactor: Interactor,
    private val mapper: DomainToUiMapper
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ListCharactersViewModel::class.java -> ListCharactersViewModel(interactor, mapper)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}