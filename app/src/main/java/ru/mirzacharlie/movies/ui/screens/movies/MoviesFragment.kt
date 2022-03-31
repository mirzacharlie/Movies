package ru.mirzacharlie.movies.ui.screens.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.mirzacharlie.movies.databinding.FragmentMoviesBinding
import ru.mirzacharlie.movies.domain.models.SearchParams
import ru.mirzacharlie.movies.ui.base.BaseFragment
import ru.mirzacharlie.movies.ui.dialogs.search.SearchDialog

class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(SearchDialog::javaClass.name) { _, bundle ->
            val params = mutableListOf<SearchParams>()

            for (i in 0 until bundle.size()) {
                val key = "${SearchDialog.KEY_RESULT}+$i"
                val param = bundle.getSerializable(key) as SearchParams
                params.add(param)
            }

            viewModel.search(params)
        }

        val adapter = MoviesAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter

        binding.fabSearch.setOnClickListener {
            SearchDialog.newInstance().show(
                parentFragmentManager,
                SearchDialog::javaClass.name
            )
        }

        adapter.onItemCLickListener = object : MoviesAdapter.OnItemCLickListener {
            override fun onItemClick(id: Int) {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(id)
                )
            }
        }

        adapter.onReachEndListener = object : MoviesAdapter.OnReachEndListener {
            override fun onReachEnd() {
                if (viewModel.state.value is FragmentState.SearchResult) return

                Log.e("FRAGMENT", "OnReachEnd!!!")
                viewModel.loadNewPage()
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is FragmentState.Popular -> it.movies.observe(viewLifecycleOwner) { movies ->
                    adapter.update(movies)
                }
                is FragmentState.SearchResult -> adapter.update(it.movies)
            }
        }
    }

}
