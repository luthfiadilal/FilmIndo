package com.example.filmindo.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.filmindo.ViewModelFactory
import com.example.filmindo.di.Injection
import com.example.filmindo.model.Film
import com.example.filmindo.model.FilmData
import com.example.filmindo.model.FilmList
import com.example.filmindo.ui.common.UiState
import com.example.filmindo.ui.screen.FilmContent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllFilm()
            }
            is UiState.Success -> {
                HomeContent(
                    filmList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun HomeContent(
    filmList: List<FilmList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(filmList) { data ->
            FilmContent(
                title = data.film.title,
                description = data.film.description,
                image = data.film.image,
                modifier = Modifier
                    .clickable {
                        navigateToDetail(data.film.id)
                    }
                    .padding(start = 14.dp)
                    .padding(end = 14.dp)
                    .padding(bottom = 8.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        }
    }
}