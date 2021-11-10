package dev.hmyh.hmyhassignmentone.data.models.impl

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.hmyh.hmyhassignmentone.data.models.BaseAppModel
import dev.hmyh.hmyhassignmentone.data.models.HmyhAssignmentOneModel
import dev.hmyh.hmyhassignmentone.data.vos.MetaVO
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO
import dev.hmyh.hmyhassignmentone.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object HmyhAssignmentOneModelImpl: BaseAppModel(),HmyhAssignmentOneModel {

    var mTopRatedMovie: MetaVO?=null

    @SuppressLint("CheckResult")
    override fun loadAllTopRatedMovieList(
        onSuccess: (topRatedMovie: TopRatedMovieVO) -> Unit,
        onFailure: (String)->Unit
    ): LiveData<TopRatedMovieVO> {

        val liveData = MutableLiveData<TopRatedMovieVO>()

        mHmyhAssignmentOneApi.loadAllTopRatedMovie(API_KEY_DATA, LANGUAGE_DATA).subscribeOn(
            Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { topRatedMovieVO ->
                    mDatabase.topRatedMovieDao().insetTopRatedMovie(topRatedMovieVO)
                        .subscribeDBWithCompletable()

                    topRatedMovieVO.movieList?.let { it1 ->
                        mDatabase.movieListDao().insetAllMovieList(it1)
                            .subscribeDBWithCompletable()
                    }
                }
            }, {})
        return liveData
    }

    override fun getAllTopRatedMovieList(onError: (String) -> Unit): LiveData<TopRatedMovieVO> {
       // return mDatabase.movieListDao().getAllMovieList()
        return mDatabase.topRatedMovieDao().getTopRatedMovie()
    }

    override fun getMovieById(movieId: Long): LiveData<MovieListVO> {
        return mDatabase.movieListDao().getMovieById(movieId)
    }


}