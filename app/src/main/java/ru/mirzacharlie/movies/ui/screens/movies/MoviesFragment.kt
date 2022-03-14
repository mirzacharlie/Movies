package ru.mirzacharlie.movies.ui.screens.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.mirzacharlie.movies.databinding.FragmentMoviesBinding
import ru.mirzacharlie.movies.di.ViewModelFactory
import ru.mirzacharlie.movies.ui.base.BaseFragment
import javax.inject.Inject

class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MoviesVM by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoviesAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter

        adapter.onItemCLickListener = object : MoviesAdapter.OnItemCLickListener {
            override fun onItemClick(id: Int) {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(id)
                )
            }
        }

        adapter.onReachEndListener = object : MoviesAdapter.OnReachEndListener {
            override fun onReachEnd() {
                Log.e("FRAGMENT", "OnReachEnd!!!")
                viewModel.loadNewPage()
            }
        }

        viewModel.result.observe(viewLifecycleOwner) {
            adapter.update(it)
        }
    }
}
