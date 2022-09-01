package com.example.movieapp.ui.fragments.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapp.adapters.GenresAdapter
import com.example.movieapp.adapters.PopularMovieAdapter
import com.example.movieapp.adapters.RecentMovieAdapter
import com.example.movieapp.viewmodels.retrofit.RetrofitViewModel
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.models.Genre
import com.example.movieapp.models.GenreX
import com.example.movieapp.models.Movie
import com.example.movieapp.models.Result
import com.example.movieapp.viewmodels.home.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private  val retrofitViewModel: RetrofitViewModel by activityViewModels()
    private val homeViewModel : HomeViewModel by activityViewModels()

    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter
    private lateinit var genreAdapter : GenresAdapter
    private lateinit var popularMovieList : List<Result>
    private lateinit var recentMovieList : List<Result>
    private lateinit var genreList : List<GenreX>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        // Popular movies için retrofit servisi çağrıldı
        retrofitViewModel.getPopularMovies(1)

        // Recent movies için retrofit servisi çağrıldı
        retrofitViewModel.getRecentVideos(1)

        // Genres için retrofit servisi çağırıldı
        retrofitViewModel.getGenreData()

        // Retrofit servisimizden gelen veriler, RetrofitViewModel içerisinde live datalar'a aktarılır. Bu method sayesinde live data'lar
        // dinlenir, içerisindeki veriler alınır, oluşturduğumuz listelere aktarılabilir ve adapter'a bağlanabilir

        listObservers(binding,retrofitViewModel.recentMovies,
            retrofitViewModel.popularMovies,
            retrofitViewModel.genreDatas)

        // RecylcerView'ların LayoutManager'ları için metod çağrıldı
        homeViewModel.settingRecyclerViewsLM(binding,requireContext())

        return binding.root
    }


    fun listObservers(binding: FragmentHomeBinding,
                      recentMovieLiveData : MutableLiveData<Movie>,
                      popularMovieLiveData: MutableLiveData<Movie>,
                      genreLiveData : MutableLiveData<Genre>,
    ){
        popularMovieLiveData.observe(viewLifecycleOwner, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                popularMovieList = t?.results as List<Result>
                popularMovieAdapter = PopularMovieAdapter(popularMovieList!!)
                binding.rvPopular.adapter = popularMovieAdapter
            }
        })
        recentMovieLiveData.observe(viewLifecycleOwner,object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                recentMovieList = t?.results as List<Result>
                recentMovieAdapter = RecentMovieAdapter(recentMovieList!!)
                binding.rvRecent.adapter = recentMovieAdapter
            }
        })
        genreLiveData.observe(viewLifecycleOwner,object : Observer<Genre> {
            override fun onChanged(t: Genre?) {
                genreList = t?.genres as List<GenreX>
                genreAdapter = GenresAdapter(genreList,true)
                binding.rvGenres.adapter = genreAdapter
            }
        })
    }


}