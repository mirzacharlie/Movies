package ru.mirzacharlie.movies.ui.screens.favourites

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.mirzacharlie.movies.databinding.FavouritesFragmentBinding
import ru.mirzacharlie.movies.ui.base.BaseFragment

class FavouritesFragment : BaseFragment<FavouritesVM, FavouritesFragmentBinding>(FavouritesFragmentBinding::inflate) {

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
