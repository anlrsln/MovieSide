package com.example.movieapp.data


import com.example.movieapp.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {


    @GET("movie/popular?")
    fun getPopularMovies(@Query("api_key") api_key:String, @Query("page") page:Int) : Call<Movie>


    @GET("movie/now_playing?")
    fun getRecentMovies(@Query("api_key") api_key:String, @Query("page") page:Int) : Call<Movie>

    @GET("genre/movie/list?")
    fun getGenres(@Query("api_key") api_key:String) : Call<Genre>

    @GET("movie/{id}/videos?")
    fun getTrailerTeasers(@Query("api_key") api_key:String,@Path("id") id:Int) : Call<Trailer>

    @GET("search/movie?")
    fun getSuggestions(@Query("api_key") api_key:String,@Query("query") query: String) : Call<Movie>

    @GET("movie/{id}/reviews?")
    fun getReviews(@Path("id") id:Int,@Query("api_key") api_key: String) : Call<Review>


}