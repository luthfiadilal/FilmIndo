package com.example.filmindo.data

import com.example.filmindo.model.Film
import com.example.filmindo.model.FilmData
import com.example.filmindo.model.FilmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FilmRepository {

    private val filmData = mutableListOf<FilmList>()

    init {
        if (filmData.isEmpty()) {
            FilmData.filmList.forEach {
                filmData.add(FilmList(it))
            }
        }
    }

    fun getAllFilm(): Flow<List<FilmList>> {
        return flowOf(filmData)
    }

    fun getFilmById(filmId: Long):FilmList {
        return filmData.first {
            it.film.id == filmId
        }
    }

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(): FilmRepository =
            instance ?: synchronized(this) {
                FilmRepository().apply {
                    instance = this
                }
            }
    }
}