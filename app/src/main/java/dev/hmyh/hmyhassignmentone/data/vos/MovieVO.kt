package dev.hmyh.hmyhassignmentone.data.vos

import com.google.gson.annotations.SerializedName

data class MovieVO(

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("original_title")
    val originalTitle: String?=null,

    @SerializedName("overview")
    val overView: String?=null,

    @SerializedName("poster_path")
    val posterPath: String?=null,

    @SerializedName("title")
    val title: String?=null,

    @SerializedName("release_date")
    val releaseDate: String?=null
)