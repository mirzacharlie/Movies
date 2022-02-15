package ru.mirzacharlie.movies.ui.screens.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.mirzacharlie.movies.data.MovieEntity
import ru.mirzacharlie.movies.databinding.ItemMovieBinding

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.MovieViewHolder>() {

    interface OnItemCLickListener {
        fun onItemClick(id: Int)
    }

    var onItemCLickListener: OnItemCLickListener? = null

    private var data: List<MovieEntity> = emptyList()

    fun update(data: List<MovieEntity>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(this.data, data))
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = data[position]

        holder.binding.imageViewPoster.load("https://image.tmdb.org/t/p/w500" + movie.posterPath)

        holder.binding.imageViewPoster.setOnClickListener {
            onItemCLickListener?.onItemClick(movie.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback(private val oldData: List<MovieEntity>, private val newData: List<MovieEntity>) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            newData[newItemPosition].id == oldData[oldItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (newData[newItemPosition].rating == oldData[oldItemPosition].rating) &&
                (newData[newItemPosition].popularity == oldData[oldItemPosition].popularity)
        }

        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size
    }
}
