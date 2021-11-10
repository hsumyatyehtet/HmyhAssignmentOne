package dev.hmyh.hmyhassignmentone.view.holder

import android.view.View
import com.bumptech.glide.Glide
import dev.hmyh.hmyhassignmentone.adapter.MovieAdapter
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import kotlinx.android.synthetic.main.view_holder_in_movie.view.*

class MovieViewHolder(itemView: View,delegate: MovieAdapter.Delegate):
    BaseViewHolder<MovieListVO>(itemView) {

    init {
        itemView.setOnClickListener {
            mData?.let { movie->
                delegate.onTapMovieItem(movie)
            }
        }
    }

    override fun bindData(data: MovieListVO) {
        mData = data

        Glide.with(itemView.context)
            .load(data.posterPath)
            .into(itemView.ivMovieImage)
        itemView.tvMovieTitle.text = data.title
    }

}