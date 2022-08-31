package com.example.movieapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultX(
    @SerializedName("id") val id:Int,
    @SerializedName("key") val key:String,
    @SerializedName("name") val name:String) : Serializable {
}