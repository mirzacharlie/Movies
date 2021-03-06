package ru.mirzacharlie.movies.ui.screens.moviedetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import ru.mirzacharlie.movies.R
import ru.mirzacharlie.movies.databinding.FragmentMovieDetailsBinding
import ru.mirzacharlie.movies.ui.base.BaseFragment

class MovieDetailsFragment :
    BaseFragment<MovieDetailsVM, FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate)
{ // ktlint-disable curly-spacing

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.requestMovie(args.id)

        binding.imageViewFav.setOnClickListener {
            viewModel.updateFavourite()
        }

        viewModel.movie.observe(viewLifecycleOwner) {
            it?.let {
                binding.imageViewPoster.load("https://image.tmdb.org/t/p/original" + it.posterPath)
                binding.textViewTitle.text = it.title
                binding.textViewRating.text = "Rating: " + it.rating
                binding.textViewDescriptionLabel.text = "Release date: " + it.releaseDate

                if (it.isFavourite == 0) {
                    binding.imageViewFav.setImageResource(R.drawable.ic_favorite_border_24)
                } else {
                    binding.imageViewFav.setImageResource(R.drawable.ic_favorite_24)
                }
            }
        }
    }
}
