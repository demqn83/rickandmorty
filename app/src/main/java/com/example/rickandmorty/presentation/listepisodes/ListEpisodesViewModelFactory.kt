package com.example.rickandmorty.presentation.listepisodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.Interactor

class ListEpisodesViewModelFactory(
    private val interactor: Interactor,
    private val mapper: DomainToUiEpisodesMapper
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ListEpisodesViewModel::class.java -> ListEpisodesViewModel(interactor, mapper)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}