package ru.mirzacharlie.movies.ui.screens.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.compose.rememberImagePainter
import ru.mirzacharlie.movies.R
import ru.mirzacharlie.movies.data.MovieEntity

//class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
//
//    interface OnItemCLickListener {
//        fun onItemClick(id: Int)
//    }
//
//    interface OnReachEndListener {
//        fun onReachEnd()
//    }
//
//    var onItemCLickListener: OnItemCLickListener? = null
//    var onReachEndListener: OnReachEndListener? = null
//
//    private var data: List<MovieEntity> = emptyList()
//
//    fun update(data: List<MovieEntity>) {
//        val diffResult = DiffUtil.calculateDiff(DiffCallback(this.data, data))
//        this.data = data
//        diffResult.dispatchUpdatesTo(this)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        val composeView = ComposeView(context = parent.context)
//        return MovieViewHolder(composeView)
//    }
//
//    @Composable
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        holder.Bind(data[position])
////
////        Log.e("ADAPTER", "Size: ${data.size}, Pos: $position")
////
////        if (position >= data.size - 1 && data.size >= 20) {
////            onReachEndListener?.onReachEnd()
////        }
////
////        val movie = data[position]
////
////        holder.itemVIew.imageViewPoster.load("https://image.tmdb.org/t/p/w220_and_h330_face" + movie.posterPath)
////
////        holder.binding.imageViewPoster.setOnClickListener {
////            onItemCLickListener?.onItemClick(movie.id)
////        }
//    }
//
//
//    override fun getItemCount(): Int = data.size
//
//    inner class MovieViewHolder(val itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {
//        private val composeView = itemView as ComposeView
//
//        @Composable
//        fun Bind(data: MovieEntity) {
//
//            Box(Modifier.clickable {
//                onItemCLickListener?.onItemClick(data.id)
//            }) {
//                Image(
//                    rememberImagePainter("https://image.tmdb.org/t/p/w220_and_h330_face" + data.posterPath),
//                    data.title
//                )
//            }
//        }
//    }
//
//    class DiffCallback(
//        private val oldData: List<MovieEntity>,
//        private val newData: List<MovieEntity>
//    ) :
//        DiffUtil.Callback() {
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
//            newData[newItemPosition].id == oldData[oldItemPosition].id
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return (newData[newItemPosition].rating == oldData[oldItemPosition].rating) &&
//                    (newData[newItemPosition].popularity == oldData[oldItemPosition].popularity)
//        }
//
//        override fun getOldListSize(): Int = oldData.size
//
//        override fun getNewListSize(): Int = newData.size
//    }
//}
