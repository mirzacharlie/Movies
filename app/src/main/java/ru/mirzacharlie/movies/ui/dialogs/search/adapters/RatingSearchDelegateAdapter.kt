package ru.mirzacharlie.movies.ui.dialogs.search.adapters

import ru.mirzacharlie.movies.databinding.ItemSearchParamRatingBinding
import ru.mirzacharlie.movies.domain.models.MovieSearchParams
import ru.mirzacharlie.movies.ui.delegateadapter.ViewBindingDelegateAdapter
import ru.mirzacharlie.movies.ui.models.RatingSearchParam

class RatingSearchDelegateAdapter :
    ViewBindingDelegateAdapter<RatingSearchParam, ItemSearchParamRatingBinding>
        (ItemSearchParamRatingBinding::inflate) {

    override var data = MovieSearchParams(null, null, null)

    override fun ItemSearchParamRatingBinding.onBind(item: RatingSearchParam) {

        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            ratingBar.rating = rating
            data = MovieSearchParams(null, rating * 2, null)
            item.value = rating
        }
    }

    override fun isForViewType(item: Any): Boolean = item is RatingSearchParam

    override fun RatingSearchParam.getItemId(): Any = name
}