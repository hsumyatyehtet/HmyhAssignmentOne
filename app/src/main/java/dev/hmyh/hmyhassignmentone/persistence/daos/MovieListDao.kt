package dev.hmyh.hmyhassignmentone.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import io.reactivex.Completable

@Dao
interface MovieListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetAllMovieList(movieList: List<MovieListVO>): Completable

    @Query("SELECT * from movie_list")
    fun getAllMovieList(): LiveData<List<MovieListVO>>

}