package ru.mirzacharlie.movies.ui.screens.favourites

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.mirzacharlie.movies.databinding.FavouritesFragmentBinding
import ru.mirzacharlie.movies.ui.base.BaseFragment

class FavouritesFragment : BaseFragment<FavouritesFragmentBinding>(FavouritesFragmentBinding::inflate) {

    private val viewModel: FavouritesVM by viewModel()

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
