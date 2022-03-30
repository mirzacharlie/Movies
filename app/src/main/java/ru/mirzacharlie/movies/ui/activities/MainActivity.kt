package ru.mirzacharlie.movies.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesScreen
import ru.mirzacharlie.movies.ui.screens.favourites.FavouritesVM
import ru.mirzacharlie.movies.ui.screens.movies.MoviesScreen
import ru.mirzacharlie.movies.ui.screens.movies.MoviesVM

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val tabs = listOf("Movies", "Favourites")
            var currentTab by remember { mutableStateOf(tabs.first()) }
            val navController = rememberNavController()

            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    when (currentTab) {
                        "Movies" -> {
                            val viewModel = hiltViewModel<MoviesVM>()
                            MoviesScreen(viewModel = viewModel, navController = navController)
                        }
                        "Favourites" -> {
                            val viewModel = hiltViewModel<FavouritesVM>()
                            FavouritesScreen(viewModel = viewModel, navController = navController)
                        }
                    }
                }
            }

            BottomNavigation(
                backgroundColor = Color.Black
            ) {
                tabs.forEach { value ->
                    val isSelected = value == currentTab
                    BottomNavigationItem(selected = isSelected, onClick = {
                        currentTab = value
                    }, icon = {

                    }, label = {
                        Text(text = value, color = if (isSelected) Color.Red else Color.White)
                    })
                }
            }
        }
    }
}
