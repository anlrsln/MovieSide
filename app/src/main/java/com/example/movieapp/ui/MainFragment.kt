package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)



        setUpTabBar()




        return binding.root

    }



    private fun setUpTabBar(){
        binding.bottomBar.setItemSelected(R.id.home_item,true)
        binding.bottomBar.setOnItemSelectedListener {
            when(it){
                R.id.home_item -> childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.homeFragment2)
                R.id.favorite_item -> childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.favoriteFragment)
                R.id.settings_item -> childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.settingsFragment)
            }
        }
    }


}