package ru.mirzacharlie.movies.ui.screens.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.mirzacharlie.movies.databinding.ItemMovieBinding
import ru.mirzacharlie.movies.ui.models.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    interface OnItemCLickListener {
        fun onItemClick(id: Int)
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }

    var onItemCLickListener: OnItemCLickListener? = null
    var onReachEndListener: OnReachEndListener? = null

    private var data: List<Movie> = emptyList()

    fun update(data: List<Movie>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(this.data, data))
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        Log.e("ADAPTER", "Size: ${data.size}, Pos: $position")

        if (position >= data.size - 1 && data.size >= 20) {
            onReachEndListener?.onReachEnd()
        }

        val movie = data[position]

        holder.binding.imageViewPoster.load("https://image.tmdb.org/t/p/w220_and_h330_face" + movie.posterPath)

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

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback(
        private val oldData: List<Movie>,
        private val newData: List<Movie>
    ) :
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
