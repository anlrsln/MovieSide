package com.example.movieapp.viewmodels.detail

import android.content.res.Resources
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.movieapp.adapters.PagerAdapter
import com.example.movieapp.data.ApiClient
import com.example.movieapp.data.ApiClient.Companion.api_key
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.models.*
import com.example.movieapp.ui.fragments.detail.MovieDetailFragmentArgs
import com.example.movieapp.viewmodels.retrofit.RetrofitViewModel
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DetailViewModel : ViewModel() {
    private lateinit var adapter : PagerAdapter
    private lateinit var movie : Result
    private lateinit var title:String
    private lateinit var background: String
    private lateinit var profileImage: String
    private lateinit var overview:String
    private lateinit var date:String
    private  var rate : Double = 0.0




    // Film detay sayfası için overview livedata
    val overViewData = MutableLiveData<String>()


    // overview verisi, moviedetailfragment sayfasına geldi ve bu metod sayesinde gelen veri livedata'ya aktarıldı
    fun setOverview(overView : String){
        overViewData.value = overView
    }



    // TabLayout ile viewpage'ı bağlayan metod
    fun setTabLayout(binding:FragmentMovieDetailBinding,activity : FragmentActivity){
        // Viewpager adapter
        adapter = PagerAdapter(activity)
        // adapter viewpager'a bağlandı
        binding.viewpager.adapter=adapter
        // Tabloyut ile viewpager bağlandı
        TabLayoutMediator(binding.tabLayout,binding.viewpager){tab,index->
            tab.text = when(index){
                0 -> {"Overview"}
                1 -> { "Review"}
                else -> {throw Resources.NotFoundException("Position Not Found")}
            }
        }.attach()
    }


    // bundle'dan gelen veriler değişkenlere atandı
    fun getBundle(bundle : MovieDetailFragmentArgs){
        movie = bundle.movie
        title = movie.title
        overview = movie.overview
        background = movie.backdrop_path
        profileImage = movie.poster_path
        date = movie.release_date
        rate = movie.vote_average
    }

    // veriler view'lara atandı
    fun setMovieDetailCard(binding:FragmentMovieDetailBinding,bundle : MovieDetailFragmentArgs){
        getBundle(bundle)
        binding.movieName.text = title
        setImages(background,binding.moviePoster,"500")
        setImages(profileImage,binding.profileImage,"342")
        binding.date.text = date
        binding.rating.text=rate.toString()
    }

    // Glide ile image'lar dönüştürüldü
    fun setImages(image:String, view: ImageView, size:String){
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/w${size}/${image}")
            .into(view)
    }





}