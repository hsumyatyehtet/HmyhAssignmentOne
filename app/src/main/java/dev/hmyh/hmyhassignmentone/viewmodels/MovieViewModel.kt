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
            onSuccess = {movieList-> },
            onFailure = {}
        )
    }

    override fun onTapMovieItem(movie: MovieListVO) {
        navigateToMovieDetailLiveData.postValue(movie)
    }

    fun getMovieById(movieId: Long): LiveData<MovieListVO>{
        return mHmyhAssignmentOneModel.getMovieById(movieId)
    }

}