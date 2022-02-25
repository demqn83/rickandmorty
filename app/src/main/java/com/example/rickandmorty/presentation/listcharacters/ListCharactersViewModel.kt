package com.example.rickandmorty.presentation.listcharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Interactor
import kotlinx.coroutines.launch

class ListCharactersViewModel(
    private val interactor: Interactor,
    private val mapper: DomainToUiMapper
) : ViewModel() {

    val listCharacters: LiveData<List<CharacterUI>>
        get() = _listCharacters
    private val _listCharacters: MutableLiveData<List<CharacterUI>> = MutableLiveData()

    init {
        _listCharacters.value = listOf(CharacterUI.Progress)
        getAllCharacters()
    }

    fun getAllCharacters() {
        viewModelScope.launch {
            _listCharacters.value = mapper.map(interactor.getAllCharacters())
        }
    }
}