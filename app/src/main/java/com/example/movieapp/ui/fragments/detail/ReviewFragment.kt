package com.example.movieapp.ui.fragments.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.ReviewsAdapter
import com.example.movieapp.databinding.FragmentReviewBinding

import com.example.movieapp.models.Author
import com.example.movieapp.models.Review
import com.example.movieapp.viewmodels.detail.DetailViewModel
import com.example.movieapp.viewmodels.retrofit.RetrofitViewModel


class ReviewFragment : Fragment() {
    private lateinit var binding : FragmentReviewBinding
    private val viewModel : DetailViewModel by activityViewModels()
    private val retrofitViewModel : RetrofitViewModel by activityViewModels()
    private lateinit var authorList: List<Author>
    private lateinit var adapter : ReviewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReviewBinding.inflate(inflater,container,false)


        binding.reviewRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)


        retrofitViewModel.reviewData.observe(viewLifecycleOwner,object : Observer<Review>{
            override fun onChanged(t: Review?) {
                if(t!=null){
                    authorList = t.results
                    adapter = ReviewsAdapter(authorList)
                    binding.reviewRv.adapter=adapter
                }
            }

        })



        return binding.root
    }

}