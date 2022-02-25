package com.example.rickandmorty.presentation.characterInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.Interactor
import kotlinx.coroutines.launch

class CharacterInfoViewModel(
    private val interactor: Interactor, private val mapper: DomainToUiFullInfoMapper
) : ViewModel() {

    val characterFullInfoUI: LiveData<CharacterFullInfoUI>
        get() = _characterFullInfoUI
    private val _characterFullInfoUI: MutableLiveData<CharacterFullInfoUI> = MutableLiveData()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _characterFullInfoUI.value = mapper.map(interactor.getCharacter(id))
        }
    }
}