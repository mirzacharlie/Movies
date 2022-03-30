package ru.mirzacharlie.movies.ui.screens.favourites

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.databinding.FavouritesFragmentBinding
import ru.mirzacharlie.movies.ui.base.BaseFragment

class FavouritesFragment :
    BaseFragment<FavouritesVM, FavouritesFragmentBinding>(FavouritesFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movies.observe(viewLifecycleOwner) {
            binding.favouritesView.setContent {
                MoviesListView(movies = it) {
                    findNavController().navigate(
                        FavouritesFragmentDirections.actionFavouritesFragmentToMovieDetailsFragment(
                            it
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun MoviesListView(movies: List<MovieEntity>, onClick: (id: Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
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


