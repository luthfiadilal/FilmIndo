package com.example.filmindo.di

import com.example.filmindo.data.FilmRepository

object Injection {

    fun provideRepository(): FilmRepository {
        return FilmRepository.getInstance()
    }
}