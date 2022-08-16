package com.example.movieapp.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.movieapp.R
import com.example.movieapp.data.RetrofitViewModel
import com.example.movieapp.databinding.FragmentSplashBinding
import com.example.movieapp.models.Genre
import com.example.movieapp.models.GenreX
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var handler: Handler= Handler()
    private var genreList : List<GenreX>? = null

    private lateinit var retrofitViewModel: RetrofitViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater,container,false)

        val tempViewModel : RetrofitViewModel by viewModels()
        retrofitViewModel = tempViewModel


        retrofitViewModel.getGenreData()

        retrofitViewModel.genreDatas.observe(viewLifecycleOwner,object  : Observer<Genre>{
            override fun onChanged(t: Genre?) {
                if(t!=null){
                    genreList = t.genres
                }
            }
        })



        binding.lottieAnimation.animate()
        val monitor : Runnable = Runnable { Navigation.findNavController(binding.root).navigate(R.id.passFromSplashToHome)}
        handler.postDelayed(monitor,5000)


        //setInterval(5000, { reloadGenres() })
        //Thread.sleep(3000) // Keep the program from exiting for a few seconds.



        return binding.root
    }






    fun setInterval(timeMillis: Long, handler: () -> Unit) = GlobalScope.launch {
        while (true) {
            delay(timeMillis)
            handler()
        }
    }


}