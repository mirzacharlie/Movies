package ru.mirzacharlie.movies.ui.dialogs.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import ru.mirzacharlie.movies.databinding.DialogSearchBinding
import ru.mirzacharlie.movies.ui.delegateadapter.CompositeDelegateAdapter
import ru.mirzacharlie.movies.ui.dialogs.search.adapters.AdultSearchDelegateAdapter
import ru.mirzacharlie.movies.ui.dialogs.search.adapters.RatingSearchDelegateAdapter
import ru.mirzacharlie.movies.ui.dialogs.search.adapters.TitleSearchDelegateAdapter
import ru.mirzacharlie.movies.ui.models.IsAdultSearchParam
import ru.mirzacharlie.movies.ui.models.RatingSearchParam
import ru.mirzacharlie.movies.ui.models.SearchParams
import ru.mirzacharlie.movies.ui.models.TitleSearchParam

class SearchDialog : DialogFragment() {

    companion object {

        final val KEY_RESULT = "result"

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

        val params = listOf<SearchParams>(
            TitleSearchParam(),
            RatingSearchParam(),
            IsAdultSearchParam()
        )

        adapter.swapData(params)

        binding.button.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                SearchDialog::javaClass.name,
                bundleOf(KEY_RESULT to adapter.getSearchParams())
            )
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}