package com.example.filmindo.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmindo.data.FilmRepository
import com.example.filmindo.model.Film
import com.example.filmindo.model.FilmList
import com.example.filmindo.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFilmViewModel(private val repository: FilmRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<FilmList>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FilmList>> get() = _uiState

    fun getFilmById(filmId: Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFilmById(filmId))
        }
    }
}