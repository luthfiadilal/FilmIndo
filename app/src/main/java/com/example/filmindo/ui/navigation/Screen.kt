package com.example.filmindo.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Profil: Screen("profil")

    object DetailFilm: Screen("home/{filmId}") {
        fun createRoute(filmId: Long) = "home/$filmId"
    }
}
