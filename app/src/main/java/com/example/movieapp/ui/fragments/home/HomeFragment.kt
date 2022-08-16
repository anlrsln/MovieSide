package com.example.movieapp.ui.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.adapters.PopularMovieAdapter
import com.example.movieapp.adapters.RecentMovieAdapter
import com.example.movieapp.data.RetrofitViewModel
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.models.Movie
import kotlinx.coroutines.*


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)


        val tempViewModel: RetrofitViewModel by viewModels()
        viewModel = tempViewModel

        fetchMovies()

        settingRecyclerViewsLM(binding.rvPopular,binding.rvRecent)

        movieObservers(viewModel.popularMovies,true)
        movieObservers(viewModel.recentMovies)



        return binding.root
    }



    fun fetchMovies() {
        CoroutineScope(Dispatchers.IO).launch{
            val job1 : Deferred<Unit> = async {
                viewModel.getPopularMovies(1)
        }
            val job2 : Deferred<Unit> = async {
                viewModel.getRecentVideos(1)
            }
            job1.await()
            job2.await()
        }
    }



    fun settingRecyclerViewsLM(rvPopular:RecyclerView, rvRecent:RecyclerView){
        val lmHorizontal = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val lmVertical = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        rvPopular.layoutManager = lmHorizontal
        rvRecent.layoutManager = lmVertical
    }

    fun movieObservers(liveData: MutableLiveData<Movie>,isPopular : Boolean = false){
        liveData.observe(viewLifecycleOwner, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(isPopular){
                    val popularMovieList = t?.results
                    popularMovieAdapter = PopularMovieAdapter(popularMovieList!!)
                    binding.rvPopular.adapter = popularMovieAdapter
                }else{
                    val recentMovieList = t?.results
                    recentMovieAdapter = RecentMovieAdapter(recentMovieList!!)
                    binding.rvRecent.adapter = recentMovieAdapter
                }
            }
        })
    }


}