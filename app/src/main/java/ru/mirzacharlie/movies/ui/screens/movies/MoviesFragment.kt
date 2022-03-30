package ru.mirzacharlie.movies.ui.screens.movies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import ru.mirzacharlie.movies.data.MovieEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(
    viewModel: MoviesVM,
    navController: NavController
) {

    val movies = viewModel.result.observeAsState().value ?: return

    MoviesListView(movies = movies) {
        navController.navigate(
            MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(it)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun MoviesListView(movies: List<MovieEntity>, onClick: (id: Int) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(count = 3),
        content = {
            movies.forEach {
                item {
                    Box(modifier = Modifier
                        .clickable {
                            onClick.invoke(it.id)
                        }
                        .height(190.dp)
                    ) {
                        Image(
                            painter = rememberImagePainter("https://image.tmdb.org/t/p/w500" + it.posterPath),
                            contentDescription = it.posterPath,
                            alignment = Alignment.Center,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
        })
}
