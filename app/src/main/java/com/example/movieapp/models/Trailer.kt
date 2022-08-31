package com.example.movieapp.models

import java.io.Serializable

data class Trailer(val id:Int,val results:List<ResultX>): Serializable {
}