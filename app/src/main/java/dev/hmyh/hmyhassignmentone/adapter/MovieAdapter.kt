package dev.hmyh.hmyhassignmentone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.hmyh.hmyhassignmentone.R
import dev.hmyh.hmyhassignmentone.data.vos.MovieVO
import dev.hmyh.hmyhassignmentone.view.holder.MovieViewHolder

class MovieAdapter: BaseRecyclerAdapter<MovieViewHolder,MovieVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_in_movie,parent,false)
        return MovieViewHolder(view)
    }

}