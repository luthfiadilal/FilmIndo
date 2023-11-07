package com.example.filmindo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmindo.model.FilmData
import com.example.filmindo.ui.navigation.NavigationItem
import com.example.filmindo.ui.navigation.Screen
import com.example.filmindo.ui.screen.FilmContent
import com.example.filmindo.ui.screen.detail.DetailScreen
import com.example.filmindo.ui.screen.home.HomeScreen
import com.example.filmindo.ui.screen.profile.ProfileScreen
import com.example.filmindo.ui.theme.FilmIndoTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JetFilmApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar ={
            if (currentRoute != Screen.DetailFilm.route){
                BottomBar(
                    navController
                )
            }
        },
        modifier = modifier
    ){innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { filmId ->
                        navController.navigate(Screen.DetailFilm.createRoute(filmId))
                    }
                )
            }
            composable(
                route = Screen.Profil.route
            ) {
                ProfileScreen(
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
            }
            composable(
                route = Screen.DetailFilm.route,
                arguments = listOf(navArgument("filmId") {type = NavType.LongType})
            ) {
                val filmId = it.arguments?.getLong("filmId") ?: -1L
                DetailScreen(
                    filmId = filmId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }

    }

}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar (
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Profil",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profil
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon ={
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(item.title)},
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJetFilm() {
    FilmIndoTheme {
        JetFilmApp()
    }
}