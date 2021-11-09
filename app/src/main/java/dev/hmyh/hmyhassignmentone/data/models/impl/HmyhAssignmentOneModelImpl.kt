package dev.hmyh.hmyhassignmentone.data.models.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.hmyh.hmyhassignmentone.data.models.BaseAppModel
import dev.hmyh.hmyhassignmentone.data.models.HmyhAssignmentOneModel
import dev.hmyh.hmyhassignmentone.data.vos.MetaVO
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO
import dev.hmyh.hmyhassignmentone.utils.*

object HmyhAssignmentOneModelImpl: BaseAppModel(),HmyhAssignmentOneModel {

    var mTopRatedMovie: MetaVO?=null

    override fun loadAllTopRatedMovieList(
        onSuccess: (topRatedMovie: TopRatedMovieVO) -> Unit,
        onFailure: (String)->Unit
    ): LiveData<TopRatedMovieVO> {

        val liveData = MutableLiveData<TopRatedMovieVO>()
        mHmyhAssignmentOneApi.loadAllTopRatedMovie(API_KEY_DATA, LANGUAGE_DATA)
            .subscribeDataResponse(
                success = {movie->

                    mDatabase.topRatedMovieDao().insetTopRatedMovie(movie)
                        .subscribeDBWithCompletable()
                },
                failure = {
                    onFailure(it)
                }
            )
        return liveData
    }

    override fun getAllTopRatedMovieList(onError: (String) -> Unit): LiveData<List<MovieListVO>> {
        return mDatabase.movieListDao().getAllMovieList()
    }


}