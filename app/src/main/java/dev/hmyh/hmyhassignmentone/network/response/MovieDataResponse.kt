package dev.hmyh.hmyhassignmentone.network.response

import com.google.gson.annotations.SerializedName

data class MovieDataResponse<T>(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("data")
    val data: T? = null
) {
    fun isResponseOk() = code in 200..299 && data != null
}