package com.example.rickandmorty.presentation.listepisodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Interactor
import kotlinx.coroutines.launch

class ListEpisodesViewModel(
    private val interactor: Interactor,
    private val mapper: DomainToUiEpisodesMapper
) : ViewModel() {

    val listEpisodes: LiveData<List<EpisodeUI>>
        get() = _listEpisodes
    private val _listEpisodes: MutableLiveData<List<EpisodeUI>> = MutableLiveData()

    init {
        _listEpisodes.value = listOf(EpisodeUI.Progress)
    }

    fun getAllEpisodes(id: Int) {
        viewModelScope.launch {
            _listEpisodes.value = mapper.map(interactor.getAllEpisodes(id))
        }
    }



}