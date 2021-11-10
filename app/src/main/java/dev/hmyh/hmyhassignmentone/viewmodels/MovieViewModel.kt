package dev.hmyh.hmyhassignmentone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.hmyh.hmyhassignmentone.adapter.MovieAdapter
import dev.hmyh.hmyhassignmentone.data.models.HmyhAssignmentOneModel
import dev.hmyh.hmyhassignmentone.data.models.impl.HmyhAssignmentOneModelImpl
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO

class MovieViewModel: ViewModel(),MovieAdapter.Delegate{

    private val mHmyhAssignmentOneModel: HmyhAssignmentOneModel = HmyhAssignmentOneModelImpl

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()
    private val navigateToMovieDetailLiveData: MutableLiveData<MovieListVO> = MutableLiveData()

    private val enableSwipeRefreshLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val disableSwipeRefreshLiveData: MutableLiveData<Unit> = MutableLiveData()

    private val movieListLiveData: LiveData<TopRatedMovieVO> = mHmyhAssignmentOneModel.getAllTopRatedMovieList {
        errorLiveData.postValue(it)
    }

    fun getMovieListLiveData(): LiveData<TopRatedMovieVO>{
        return movieListLiveData
    }

    fun getNavigateToMovieDetailLiveData(): LiveData<MovieListVO>{
        return navigateToMovieDetailLiveData
    }
    
    fun onUiReady(){
        mHmyhAssignmentOneModel.loadAllTopRatedMovieList(
            onSuccess = {movieList->

            },
            onFailure = {

            }
        )
    }

    fun getEnableSwipeRefreshLiveData() : LiveData<Unit>{
        return enableSwipeRefreshLiveData
    }

    fun getDisableSwipeRefreshLiveData() : LiveData<Unit>{
        return disableSwipeRefreshLiveData
    }

    fun onSwipeRefresh(){
        enableSwipeRefreshLiveData.postValue(Unit)
        mHmyhAssignmentOneModel.loadAllTopRatedMovieList(
            onSuccess = {movieList->
                disableSwipeRefreshLiveData.postValue(Unit)
            },
            onFailure = {
                disableSwipeRefreshLiveData.postValue(Unit)
                errorLiveData.postValue(it)
            }
        )
    }

    override fun onTapMovieItem(movie: MovieListVO) {
        navigateToMovieDetailLiveData.postValue(movie)
    }

}