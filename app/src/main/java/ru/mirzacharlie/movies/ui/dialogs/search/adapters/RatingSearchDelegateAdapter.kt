package ru.mirzacharlie.movies.ui.dialogs.search.adapters

import ru.mirzacharlie.movies.databinding.ItemSearchParamRatingBinding
import ru.mirzacharlie.movies.domain.models.SearchParams
import ru.mirzacharlie.movies.ui.delegateadapter.ViewBindingDelegateAdapter

class RatingSearchDelegateAdapter :
    ViewBindingDelegateAdapter<SearchParams.RatingSearchParamModel, ItemSearchParamRatingBinding>
        (ItemSearchParamRatingBinding::inflate) {

    override var data: SearchParams = SearchParams.RatingSearchParamModel()

    override fun ItemSearchParamRatingBinding.onBind(item: SearchParams.RatingSearchParamModel) {

        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            ratingBar.rating = rating
            data = SearchParams.RatingSearchParamModel(value = rating * 2)
            item.value = rating
        }
    }

    override fun isForViewType(item: Any): Boolean = item is SearchParams.RatingSearchParamModel

    override fun SearchParams.RatingSearchParamModel.getItemId(): Any = hint
}