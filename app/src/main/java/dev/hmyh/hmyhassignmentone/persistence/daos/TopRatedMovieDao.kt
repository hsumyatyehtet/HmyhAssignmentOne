package dev.hmyh.hmyhassignmentone.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO
import io.reactivex.Completable

@Dao
interface TopRatedMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetTopRatedMovie(topRatedMovie: TopRatedMovieVO): Completable

    @Query("SELECT * from top_rated_movie")
    fun getTopRatedMovie(): LiveData<TopRatedMovieVO>

}