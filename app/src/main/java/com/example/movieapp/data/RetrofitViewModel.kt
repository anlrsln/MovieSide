package com.example.movieapp.data


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Genre
import com.example.movieapp.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitViewModel : ViewModel() {


    val api_key = ApiClient.api_key
    var popularMovies : MutableLiveData<Movie> = MutableLiveData()
    var recentMovies : MutableLiveData<Movie> = MutableLiveData()
    var genreDatas : MutableLiveData<Genre> = MutableLiveData()



    fun getPopularMovies(page:Int){
        ApiClient.getApiServiceInstance().getPopularMovies(api_key,page).enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    popularMovies.value = response.body()
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("Error : ",t.message.toString())
            }
        })
    }



    fun getRecentVideos(page:Int){
        ApiClient.getApiServiceInstance().getRecentMovies(api_key, page).enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    recentMovies.value = response.body()
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("Error : ",t.message.toString())
            }
        })
    }



    fun getGenreData(){
        ApiClient.getApiServiceInstance().getGenres(api_key).enqueue(object : Callback<Genre>{
            override fun onResponse(call: Call<Genre>, response: Response<Genre>) {
                if(response.isSuccessful){
                    genreDatas.value = response.body()
                }
            }
            override fun onFailure(call: Call<Genre>, t: Throwable) {
                Log.e("Error : ",t.message.toString())
            }

        })
    }





}