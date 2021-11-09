package dev.hmyh.hmyhassignmentone.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO

class MovieTypeConverter {

    @TypeConverter
    fun fromListToGson(list: List<MovieListVO>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromGsonToList(json: String): List<MovieListVO>? {
        val typeToken = object : TypeToken<List<MovieListVO>>() {}.type
        return Gson().fromJson(json, typeToken)
    }
}