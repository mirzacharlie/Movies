package ru.mirzacharlie.movies.ui.screens.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.mirzacharlie.movies.R
import ru.mirzacharlie.movies.databinding.FragmentMoviesBinding
import ru.mirzacharlie.movies.ui.base.BaseFragment

class MoviesFragment : BaseFragment<MoviesVM, FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoviesAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter

        adapter.onItemCLickListener = object : MoviesAdapter.OnItemCLickListener {
            override fun onItemClick(id: Int) {
                findNavController().navigate(R.id.movieDetailsFragment, bundleOf("id" to id))
            }
        }

        adapter.onReachEndListener = object : MoviesAdapter.OnReachEndListener {
            override fun onReachEnd() {
                Log.e("FRAGMENT", "OnReachEnd!!!")
                viewModel.loadNewPage()
            }
        }

        viewModel.result.observe(viewLifecycleOwner) {
            adapter.update(it.sortedByDescending { it.popularity })
        }
    }
}
