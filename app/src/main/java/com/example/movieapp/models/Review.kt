package com.example.movieapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Review(
    @SerializedName ("id") val id:Int,
    @SerializedName("page") val page:Int,
    @SerializedName("results") val results:List<Author>,
    @SerializedName("total_pages") val total_pages:Int,
    @SerializedName("total_results") val total_results:Int,
) : Serializable {
}