package ru.mirzacharlie.movies.ui.screens.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.mirzacharlie.movies.appComponent
import ru.mirzacharlie.movies.databinding.FavouritesFragmentBinding
import ru.mirzacharlie.movies.di.ViewModelFactory
import ru.mirzacharlie.movies.ui.base.BaseFragment
import javax.inject.Inject

class FavouritesFragment : BaseFragment<FavouritesFragmentBinding>(FavouritesFragmentBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FavouritesVM by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().appComponent.favouritesComponent().build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavouritesAdapter()
        binding.recyclerViewFav.adapter = adapter

        adapter.onItemCLickListener = object : FavouritesAdapter.OnItemCLickListener {
            override fun onItemClick(id: Int) {
                findNavController().navigate(
                    FavouritesFragmentDirections.actionFavouritesFragmentToMovieDetailsFragment(id)
                )
            }
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.update(it)
        }
    }
}
