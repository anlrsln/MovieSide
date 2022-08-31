package com.example.movieapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ReviewCardBinding
import com.example.movieapp.models.Author

class ReviewsAdapter (val reviewList : List<Author>): RecyclerView.Adapter<ReviewsAdapter.ReviewCardViewHolder>() {

    inner class ReviewCardViewHolder(val binding : ReviewCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewCardViewHolder {
        val binding = ReviewCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReviewCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewCardViewHolder, position: Int) {

        val review = reviewList[position]
        var image = review.author_details.avatar_path


        holder.binding.authorName.text = review.author_details.username ?: review.author
        holder.binding.rate.text = review.author_details.rating?.toString() ?: "No Rate"
        holder.binding.review.text = review.content
        if(image != null){
            if(image.contains("https"))
                image = image.subSequence(1,image.lastIndex).toString()
            else
                image = "https://gravatar.com/avatar${image}"

            //Log.e("Image : ",image)

            Glide.with(holder.binding.profileImage)
                .load("${image}")
                .into(holder.binding.profileImage)

        }else{
            holder.binding.profileImage.setImageResource(R.drawable.profilepic)
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}