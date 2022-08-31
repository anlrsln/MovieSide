package com.example.movieapp.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.movieapp.databinding.FragmentOverviewBinding
import com.example.movieapp.viewmodels.detail.DetailViewModel


class OverviewFragment : Fragment() {


    private lateinit var binding: FragmentOverviewBinding
    private  val viewModel: DetailViewModel by activityViewModels()
    private var overview : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOverviewBinding.inflate(inflater,container,false)



        viewModel.overViewData.observe(viewLifecycleOwner,object : Observer<String>{
            override fun onChanged(t: String?) {
                if(t!=null){
                    overview = t
                    binding.overview.text = overview
                }
            }
        })


        return binding.root
    }


}