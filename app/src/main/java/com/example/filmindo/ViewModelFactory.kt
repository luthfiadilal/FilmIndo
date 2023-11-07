package com.example.filmindo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmindo.data.FilmRepository
import com.example.filmindo.ui.screen.detail.DetailFilmViewModel
import com.example.filmindo.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: FilmRepository) :
    ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailFilmViewModel::class.java)) {
            return DetailFilmViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}