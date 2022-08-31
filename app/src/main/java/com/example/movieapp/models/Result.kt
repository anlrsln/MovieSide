package com.example.movieapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(
    @SerializedName("adult") val adult:Boolean,
    @SerializedName("backdrop_path") val backdrop_path:String,
    @SerializedName("genre_ids") val genre_ids:List<Int>,
    @SerializedName("id") val id:Int,
    @SerializedName("overview") val overview:String,
    @SerializedName("popularity") val popularity:Double,
    @SerializedName("poster_path") val poster_path:String,
    @SerializedName("release_date") val release_date:String,
    @SerializedName("title") val title:String,
    @SerializedName("vote_average") val vote_average:Double,
):Serializable
