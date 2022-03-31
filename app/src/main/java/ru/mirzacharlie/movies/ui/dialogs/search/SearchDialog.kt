package ru.mirzacharlie.movies.ui.dialogs.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.mirzacharlie.movies.databinding.DialogSearchBinding
import ru.mirzacharlie.movies.domain.models.SearchParams
import ru.mirzacharlie.movies.ui.delegateadapter.CompositeDelegateAdapter
import ru.mirzacharlie.movies.ui.dialogs.search.adapters.AdultSearchDelegateAdapter
import ru.mirzacharlie.movies.ui.dialogs.search.adapters.RatingSearchDelegateAdapter
import ru.mirzacharlie.movies.ui.dialogs.search.adapters.TitleSearchDelegateAdapter

class SearchDialog : DialogFragment() {

    companion object {

        const val KEY_RESULT = "result"

        fun newInstance(): SearchDialog {
            val args = Bundle()

            val fragment = SearchDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: DialogSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CompositeDelegateAdapter(
            listOf(
                TitleSearchDelegateAdapter(),
                RatingSearchDelegateAdapter(),
                AdultSearchDelegateAdapter()
            )
        )

        binding.recyclerViewParams.adapter = adapter

        val params = listOf(
            SearchParams.TitleSearchParamModel(),
            SearchParams.RatingSearchParamModel(),
            SearchParams.AdultSearchParamModel()
        )

        adapter.swapData(params)

        binding.button.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                SearchDialog::javaClass.name,
                bundleWithSearchParams(adapter.getSearchParams())
            )
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bundleWithSearchParams(list: List<SearchParams>): Bundle {
        val bundle = Bundle()

        list.forEachIndexed { index, param ->
            bundle.putSerializable("$KEY_RESULT+$index", param)
        }

        return bundle
    }
}