package ru.mirzacharlie.movies.ui.screens.movies

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.databinding.FragmentMoviesBinding
import ru.mirzacharlie.movies.ui.base.BaseFragment

class MoviesFragment :
    BaseFragment<MoviesVM, FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerMovies = binding.recyclerMovies

//        adapter.onReachEndListener = object : MoviesAdapter.OnReachEndListener {
//            override fun onReachEnd() {
//                Log.e("FRAGMENT", "OnReachEnd!!!")
//                viewModel.loadNewPage()
//            }
//        }

        viewModel.result.observe(viewLifecycleOwner) {
            recyclerMovies.setContent {
                MoviesListView(movies = it) {
                    findNavController().navigate(
                        MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(123)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesListView(movies: List<MovieEntity>, onClick: () -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(count = 2),
        content = {
            movies.forEach {
                item {
                    Box(modifier = Modifier.clickable { onClick.invoke() }) {
                        Image(
                            painter = rememberImagePainter("https://image.tmdb.org/t/p/w220_and_h330_face" + it.posterPath),
                            contentDescription = it.posterPath
                        )
                    }
                }
            }
        })
}
