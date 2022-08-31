package com.example.movieapp.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.movieapp.viewmodels.retrofit.RetrofitViewModel
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.models.Genre
import com.example.movieapp.models.GenreX
import com.example.movieapp.viewmodels.detail.DetailViewModel
import java.util.ArrayList


class MovieDetailFragment : Fragment() {

    private lateinit var binding : FragmentMovieDetailBinding
    private  val viewModel: DetailViewModel by activityViewModels()
    private val retrofitViewModel : RetrofitViewModel by activityViewModels()
    private lateinit var genre_list_bundle : List<Int>
    private lateinit var genre_list_livedata: List<GenreX>
    private  var genre_names = ArrayList<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        // Toolbar Ayarları
        binding.toolbar.title = "Detail"

        // TabLayout-ViewPager metodu
        viewModel.setTabLayout(binding,requireActivity())

        // bundle objesiyle args'lar alındı
        val bundle : MovieDetailFragmentArgs by navArgs()
        // args içerisindeki genre_ids listesi alındı
        genre_list_bundle = bundle.genreIdsBundle.genre_ids

        // Genre verileri çekildi ve listelere aktarıldı
        setGenreData(retrofitViewModel,binding)

        // Veriler view'lara atandı
        viewModel.setMovieDetailCard(binding,bundle)


        // Viewpager OvervieFragment'ına viewmodel aracılığıyla değerler atandı
        viewModel.setOverview(bundle.movie.overview)

        //Film kullanıcı verileri ve yorumları için retrofit servisi çağrıldı
        retrofitViewModel.getReviews(bundle.movie.id)


        return binding.root
    }


    fun setGenreData(retrofitViewModel: RetrofitViewModel, binding: FragmentMovieDetailBinding){
        // Genre verisi retrofitViewModel içerisinde getGenreData() ile detay sayfasına çekildi
        retrofitViewModel.getGenreData()

        // Gelen genre verileri, retrofitViewModel içerisindeki genreData livedata'sından alınıp
        // genre_list_livedata List'ine aktarıldı.
        retrofitViewModel.genreDatas.observe(viewLifecycleOwner,object : Observer<Genre> {
            override fun onChanged(t: Genre?) {
                if(t!=null){
                    genre_list_livedata = t.genres
                    // genre_names listemiz lateinit olarak tanımlı. İlk tetiklenmede  genre_names içerisine
                    // genre name'ler aktarıldı. Diğer tetiklenmelerde artık boş olmayacağı için bu blok
                    // bir daha çalışmayacak.
                    if(genre_names.isEmpty()){
                        // servisten gelen ve livedata'ya aktarılan genre verileri, döngüyle tek tek kontrol edildi
                        for(i in genre_list_livedata){
                            // movie objesinden gelen genre id'leri döngüyle tek tek kontrol edildi
                            for(z in genre_list_bundle){
                                // livedata genre_id, movie verisinden gelen genre_id'ye eşitse genre_names listesine
                                // genre isimleri aktarıldı
                                if(i.id == z){
                                    genre_names.add(i.name)
                                }
                            }
                        }
                        // genre_name içerisindeki name'leri ekranda
                        // (Action, Adventure, Fantasy) olarak gösterebilmek için aşağıdaki kod yazıldı
                        var name = genre_names[0]
                        for(n in genre_names){
                            if(n!=genre_names[0]){
                                name = name+", "+n
                            }
                        }
                        // yazılan name değişkeni ekrana aktarıldı
                        binding.genreText.text=name
                    }
                }
            }
        })
    }











}