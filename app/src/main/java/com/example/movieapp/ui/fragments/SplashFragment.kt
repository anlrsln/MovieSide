package com.example.movieapp.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.movieapp.R
import com.example.movieapp.viewmodels.retrofit.RetrofitViewModel
import com.example.movieapp.databinding.FragmentSplashBinding
import com.example.movieapp.models.Genre
import com.example.movieapp.models.GenreX
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var handler: Handler= Handler()
    private var genreList : List<GenreX>? = null
    private val retrofitViewModel: RetrofitViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater,container,false)


        binding.lottieAnimation.animate()
        val monitor : Runnable = Runnable { Navigation.findNavController(binding.root).navigate(R.id.passFromSplashToHome)}
        handler.postDelayed(monitor,5000)


        return binding.root
    }

}