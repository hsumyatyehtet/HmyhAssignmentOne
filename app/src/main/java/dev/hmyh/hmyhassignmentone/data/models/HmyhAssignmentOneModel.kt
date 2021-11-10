package dev.hmyh.hmyhassignmentone.data.models

import androidx.lifecycle.LiveData
import dev.hmyh.hmyhassignmentone.data.vos.MetaVO
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO

interface HmyhAssignmentOneModel {

    fun loadAllTopRatedMovieList(
        onSuccess:(topRatedMovie: TopRatedMovieVO)->Unit,
        onFailure: (String)->Unit
    ): LiveData<TopRatedMovieVO>

    fun getAllTopRatedMovieList(onError: (String)->Unit): LiveData<TopRatedMovieVO>
}