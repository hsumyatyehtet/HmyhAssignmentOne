package dev.hmyh.hmyhassignmentone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.hmyh.hmyhassignmentone.data.models.HmyhAssignmentOneModel
import dev.hmyh.hmyhassignmentone.data.models.impl.HmyhAssignmentOneModelImpl
import dev.hmyh.hmyhassignmentone.data.vos.MetaVO
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO

class MovieViewModel: ViewModel() {

    private val mHmyhAssignmentOneModel: HmyhAssignmentOneModel = HmyhAssignmentOneModelImpl

    private var mTopRatedMovieList: MutableList<MovieListVO> = mutableListOf()
    private var mMeta: MetaVO?=null

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    private val movieListLiveData: LiveData<List<MovieListVO>> = mHmyhAssignmentOneModel.getAllTopRatedMovieList {
        errorLiveData.postValue(it)
    }

    fun getMovieListLiveData(): LiveData<List<MovieListVO>>{
        return movieListLiveData
    }

    fun getErrorLivedata(): LiveData<String>{
        return errorLiveData
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