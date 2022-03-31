package ru.mirzacharlie.movies.ui.dialogs.search.adapters

import android.text.Editable
import android.text.TextWatcher
import ru.mirzacharlie.movies.databinding.ItemSearchParamStringBinding
import ru.mirzacharlie.movies.domain.models.SearchParams
import ru.mirzacharlie.movies.ui.delegateadapter.ViewBindingDelegateAdapter

class TitleSearchDelegateAdapter :
    ViewBindingDelegateAdapter<SearchParams.TitleSearchParamModel, ItemSearchParamStringBinding>
        (ItemSearchParamStringBinding::inflate) {

    override var data: SearchParams = SearchParams.TitleSearchParamModel()

    override fun ItemSearchParamStringBinding.onBind(item: SearchParams.TitleSearchParamModel) {
        editTextValue.hint = item.hint

        editTextValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val text = s?.toString()?.trim() ?: return
                item.value = text
                data = SearchParams.TitleSearchParamModel(value = text)
            }
        })
    }

    override fun isForViewType(item: Any): Boolean = item is SearchParams.TitleSearchParamModel

    override fun SearchParams.TitleSearchParamModel.getItemId(): Any = hint
}