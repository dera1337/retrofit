package Retrofit.App.main

import Retrofit.App.databinding.ActivityMainBinding
import Retrofit.App.view_model.MyViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewModel.apply {
                executeGetPopularMovies(1)

                initializeRecyclerView()

                popularMovies.observe(this@MainActivity, {
                    Log.i("PopularMovies", "$it")
                    adapter.populateData(it)
                })
            }
        }
    }

    private fun initializeRecyclerView() {
        adapter = MoviesAdapter()
        binding.apply {
            rvMovies.adapter=adapter
            rvMovies.layoutManager=LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false)
        }
    }
}