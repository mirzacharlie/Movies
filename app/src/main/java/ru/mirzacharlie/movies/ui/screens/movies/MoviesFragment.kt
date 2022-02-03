package ru.mirzacharlie.movies.ui.screens.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import ru.mirzacharlie.movies.databinding.FragmentMainBinding
import ru.mirzacharlie.movies.ui.base.BaseFragment

class MoviesFragment : BaseFragment<MoviesVM, FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoviesAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter

        adapter.onItemCLickListener = object : MoviesAdapter.OnItemCLickListener {
            override fun onItemClick(id: Int) {
                Toast.makeText(requireContext(), "Movie ID: $id", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.onReachEndListener = object : MoviesAdapter.OnReachEndListener {
            override fun onReachEnd() {
                Toast.makeText(requireContext(), "End", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.request()

        viewModel.result.observe(viewLifecycleOwner) {
            if (it.isEmpty()) return@observe

            adapter.update(it)
        }
    }
}
