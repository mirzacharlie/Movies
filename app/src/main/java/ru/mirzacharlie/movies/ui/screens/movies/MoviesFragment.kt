package ru.mirzacharlie.movies.ui.screens.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.ui.base.BaseFragment

class MoviesFragment : BaseFragment<MoviesVM>() {

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
    viewModel: MoviesVM,
    navController: NavController
){

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
