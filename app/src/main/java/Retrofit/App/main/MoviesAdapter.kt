package Retrofit.App.main

import Retrofit.App.Model.Result
import Retrofit.App.Util.Constants.IMAGE_BASE_URL
import Retrofit.App.databinding.ItemMoviesBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemMoviesBinding
    private val listContainer = mutableListOf<Result>()

    inner class ViewHolder(binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val movie = listContainer[position]
        binding.apply{
            Glide.with(holder.itemView).load("${IMAGE_BASE_URL}${movie.poster_path}").into(ivPoster)
            tvTitle.text = movie.title
            tvReleaseDate.text = movie.release_date
        }
    }

    override fun getItemCount(): Int {
        return listContainer.size
    }

    fun populateData(input: List<Result>){
        listContainer.clear()
        listContainer.addAll(input)
        notifyDataSetChanged()
    }

}