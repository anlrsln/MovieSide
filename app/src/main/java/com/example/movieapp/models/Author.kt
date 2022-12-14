package com.example.movieapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Author(
    @SerializedName("author") val author:String,
    @SerializedName("author_details") val author_details:AuthorDetails,
    @SerializedName("content") val content : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("id") val id:String,
    @SerializedName("updated_at") val updated_at:String,
    @SerializedName("url") val url:String
):Serializable{
}