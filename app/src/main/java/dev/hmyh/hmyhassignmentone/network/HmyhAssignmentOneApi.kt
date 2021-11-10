package dev.hmyh.hmyhassignmentone.network

import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO
import dev.hmyh.hmyhassignmentone.network.response.DataResponse
import dev.hmyh.hmyhassignmentone.network.response.MoreDataResponse
import dev.hmyh.hmyhassignmentone.utils.GET_ALL_TOP_RATED_MOVIE
import dev.hmyh.hmyhassignmentone.utils.PARAM_API_KEY
import dev.hmyh.hmyhassignmentone.utils.PARAM_LANGUAGE
import io.reactivex.Observable
import retrofit2.http.*

interface HmyhAssignmentOneApi {

//    @GET(GET_ALL_TOP_RATED_MOVIE)
//    fun loadAllTopRatedMovie(
//        @Query(PARAM_API_KEY)apiKey: String,
//        @Query(PARAM_LANGUAGE)language: String
//    ):Observable<DataResponse<TopRatedMovieVO>>

    @GET(GET_ALL_TOP_RATED_MOVIE)
    fun loadAllTopRatedMovie(
        @Query(PARAM_API_KEY)apiKey: String,
        @Query(PARAM_LANGUAGE)language: String
    ):Observable<TopRatedMovieVO>

}