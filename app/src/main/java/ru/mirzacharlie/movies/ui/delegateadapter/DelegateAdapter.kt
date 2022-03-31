package ru.mirzacharlie.movies.ui.delegateadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.mirzacharlie.movies.domain.models.SearchParams


interface DelegateAdapter {

    var data: SearchParams

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    fun onBindViewHolder(holder: ViewHolder, items: List<Any>, position: Int)
    fun onRecycled(holder: ViewHolder)

    fun isForViewType(items: List<Any>, position: Int): Boolean

    fun itemId(item: Any): Any

    fun itemContent(item: Any): Any

    fun onAttachedToWindow(holder: ViewHolder)

    fun onDetachedFromWindow(holder: ViewHolder)
}