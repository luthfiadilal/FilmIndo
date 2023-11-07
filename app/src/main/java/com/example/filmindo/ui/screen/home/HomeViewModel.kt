package com.example.filmindo.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmindo.data.FilmRepository
import com.example.filmindo.model.Film
import com.example.filmindo.model.FilmList
import com.example.filmindo.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FilmRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FilmList>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<FilmList>>> get() = _uiState

    fun getAllFilm(){
        viewModelScope.launch {
            repository.getAllFilm()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}