package Retrofit.App

import Retrofit.App.Util.Constants.API_KEY
import Retrofit.App.Util.Resource
import Retrofit.App.repository.MovieRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    fun executeGetPopularMovies(page: Int) = viewModelScope.launch {
        when (val resource = repo.getPopularMovies(API_KEY, page)) {
            is Resource.Failed -> {
                Log.i("Retrofit", "Error bro")
            }
            is Resource.Ok -> {
                Log.i("Retrofit", "${resource.data}")
            }
        }
    }
}