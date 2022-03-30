package ru.mirzacharlie.movies.ui.screens.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.ui.base.BaseFragment
import ru.mirzacharlie.movies.ui.screens.movies.MoviesFragmentDirections

class FavouritesFragment : BaseFragment<FavouritesVM>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(context = requireContext()).apply {
        setContent {
            CarryScreen(viewModel = viewModel, navController = findNavController())
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarryScreen(
    viewModel: FavouritesVM,
    navController: NavController
) {

    val movies = viewModel.movies.observeAsState().value ?: return

    MoviesListView(movies = movies) {
        navController.navigate(
            MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(it)
        )
    }
}

@Composable
fun MoviesListView(movies: List<MovieEntity>, onClick: (id: Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().background(color = Color.Black),
        content = {
            movies.forEach {
                item {
                    Row(
                        modifier = Modifier
                            .clickable { onClick.invoke(it.id) }
                            .fillMaxWidth()
                            .height(600.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = rememberImagePainter("https://image.tmdb.org/t/p/original" + it.posterPath),
                            contentDescription = it.posterPath
                        )
                    }
                }
            }
        })

}


