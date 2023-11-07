package com.example.filmindo.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.filmindo.R
import com.example.filmindo.ViewModelFactory
import com.example.filmindo.di.Injection
import com.example.filmindo.model.FilmData
import com.example.filmindo.ui.common.UiState
import com.example.filmindo.ui.theme.FilmIndoTheme

@Composable
fun DetailScreen(
    filmId : Long,
    viewModel: DetailFilmViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFilmById(filmId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.film.image,
                    title = data.film.title,
                    description = data.film.description,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun DetailContent(
    image: Int,
    title: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
//                AsyncImage(
//                    model = image,
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = modifier
//                        .height(400.dp)
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
//                )
                Icon(
                    imageVector =Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = description,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    FilmIndoTheme {
        DetailContent(
            image = R.drawable.mariposa,
            title = "Mariposa",
            description = stringResource(R.string.desc_mariposa),
            onBackClick = {}
        )

    }
}