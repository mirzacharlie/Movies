package ru.mirzacharlie.movies.ui.dialogs.search.adapters

import ru.mirzacharlie.movies.databinding.ItemSearchParamBooleanBinding
import ru.mirzacharlie.movies.domain.models.MovieSearchParams
import ru.mirzacharlie.movies.ui.delegateadapter.ViewBindingDelegateAdapter
import ru.mirzacharlie.movies.ui.models.IsAdultSearchParam

class AdultSearchDelegateAdapter :
    ViewBindingDelegateAdapter<IsAdultSearchParam, ItemSearchParamBooleanBinding>
        (ItemSearchParamBooleanBinding::inflate) {

    override var data = MovieSearchParams(null, null, false)

    override fun ItemSearchParamBooleanBinding.onBind(item: IsAdultSearchParam) {
        textViewParamName.text = item.name

        checkBoxValue.setOnCheckedChangeListener { _, isChecked ->
            item.value = isChecked
            data = MovieSearchParams(null, null, isChecked)
        }
    }

    override fun isForViewType(item: Any): Boolean = item is IsAdultSearchParam

    override fun IsAdultSearchParam.getItemId(): Any = name
}