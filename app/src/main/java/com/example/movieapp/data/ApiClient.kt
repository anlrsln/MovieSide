package com.example.movieapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    companion object{
        val BASE_URL = "https://api.themoviedb.org/3/"
        val api_key = "161083c39d6ffee02a86454d7954f334"
        var movieApi : RetrofitService? = null



        // uygulama ayağa kalktıktan sonra tekrar tekrar servis oluşturmak yerine bir kere oluşturup kullanmak için
        // getApiServiceInstance fonksiyonunu kullanırız
        fun getApiServiceInstance() : RetrofitService{
            if(movieApi==null){
                movieApi = ApiClient.getApiService()
            }
            return movieApi as RetrofitService
        }



        fun getApiService():RetrofitService{
            val retrofit :Retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }


    }





}