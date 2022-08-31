package com.example.movieapp.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.ui.fragments.detail.OverviewFragment
import com.example.movieapp.ui.fragments.detail.ReviewFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
     override fun getItemCount() = 2

     override fun createFragment(position: Int): Fragment {
         return when(position){
             0 -> {OverviewFragment()}
             1 -> { ReviewFragment()}
             else -> {throw Resources.NotFoundException("Position Not Found")}
         }
     }
 }