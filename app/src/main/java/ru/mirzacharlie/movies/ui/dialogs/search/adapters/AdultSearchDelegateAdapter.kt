package ru.mirzacharlie.movies.ui.dialogs.search.adapters

import ru.mirzacharlie.movies.databinding.ItemSearchParamBooleanBinding
import ru.mirzacharlie.movies.domain.models.SearchParams
import ru.mirzacharlie.movies.ui.delegateadapter.ViewBindingDelegateAdapter

class AdultSearchDelegateAdapter :
    ViewBindingDelegateAdapter<SearchParams.AdultSearchParamModel, ItemSearchParamBooleanBinding>
        (ItemSearchParamBooleanBinding::inflate) {

    override var data: SearchParams = SearchParams.AdultSearchParamModel()

    override fun ItemSearchParamBooleanBinding.onBind(item: SearchParams.AdultSearchParamModel) {
        textViewParamName.text = item.hint

        checkBoxValue.setOnCheckedChangeListener { _, isChecked ->
            item.value = isChecked
            data = SearchParams.AdultSearchParamModel(value = isChecked)
        }
    }

    override fun isForViewType(item: Any): Boolean = item is SearchParams.AdultSearchParamModel

    override fun SearchParams.AdultSearchParamModel.getItemId(): Any = hint
}