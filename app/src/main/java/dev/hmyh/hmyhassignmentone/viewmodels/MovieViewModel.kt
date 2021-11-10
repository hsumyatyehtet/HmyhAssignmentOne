package dev.hmyh.hmyhassignmentone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.hmyh.hmyhassignmentone.data.models.HmyhAssignmentOneModel
import dev.hmyh.hmyhassignmentone.data.models.impl.HmyhAssignmentOneModelImpl
import dev.hmyh.hmyhassignmentone.data.vos.MetaVO
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO

class MovieViewModel: ViewModel() {

    private val mHmyhAssignmentOneModel: HmyhAssignmentOneModel = HmyhAssignmentOneModelImpl

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    private val movieListLiveData: LiveData<TopRatedMovieVO> = mHmyhAssignmentOneModel.getAllTopRatedMovieList {
        errorLiveData.postValue(it)
    }

    fun getMovieListLiveData(): LiveData<TopRatedMovieVO>{
        return movieListLiveData
    }

    fun onUiReady(){
        mHmyhAssignmentOneModel.loadAllTopRatedMovieList(
            onSuccess = {movieList->

            },
            onFailure = {

            }
        )
    }

}