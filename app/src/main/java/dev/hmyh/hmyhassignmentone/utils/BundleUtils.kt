package dev.hmyh.hmyhassignmentone.utils

import android.os.Bundle
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO

fun getBundleMovieDetail(movieId: Long): Bundle{

    return Bundle().apply {
        putLong("movie_id",movieId)
    }

}