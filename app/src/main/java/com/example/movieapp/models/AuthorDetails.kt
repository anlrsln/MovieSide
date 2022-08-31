package com.example.movieapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthorDetails(
    @SerializedName("name") val name:String,
    @SerializedName("username") val username:String? = null,
    @SerializedName("avatar_path") val avatar_path:String? = null,
    @SerializedName("rating") val rating:Double? = null
) : Serializable {
}