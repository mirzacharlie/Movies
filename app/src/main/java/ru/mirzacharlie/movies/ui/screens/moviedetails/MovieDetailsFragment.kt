package ru.mirzacharlie.movies.ui.screens.moviedetails

import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.NavController

class MovieDetailsFragment : Fragment() { // ktlint-disable curly-spacing

//    private var _binding: FragmentMovieDetailsBinding? = null
//    private val binding: FragmentMovieDetailsBinding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentMovieDetailsBinding.inflate(inflater)
//        return binding.root
//    }
//
//    private val args: MovieDetailsFragmentArgs by navArgs()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.requestMovie(args.id)
//
//        binding.imageViewFav.setOnClickListener {
//            viewModel.updateFavourite()
//        }
//
//        viewModel.movie.observe(viewLifecycleOwner) {
//            it?.let {
//                binding.imageViewPoster.load("https://image.tmdb.org/t/p/original" + it.posterPath)
//                binding.textViewTitle.text = it.title
//                binding.textViewRating.text = "Rating: " + it.rating
//                binding.textViewDescriptionLabel.text = "Release date: " + it.releaseDate
//
//                if (it.isFavourite == 0) {
//                    binding.imageViewFav.setImageResource(R.drawable.ic_favorite_border_24)
//                } else {
//                    binding.imageViewFav.setImageResource(R.drawable.ic_favorite_24)
//                }
//            }
//        }
//    }

}

@Composable
fun DetailsScreen(viewModel: MovieDetailsVM, navController: NavController) {

}