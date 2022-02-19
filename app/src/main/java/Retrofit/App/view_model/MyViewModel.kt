package Retrofit.App.view_model

import Retrofit.App.Model.PopularMovieResponse
import Retrofit.App.Model.Result
import Retrofit.App.Util.Constants.API_KEY
import Retrofit.App.Util.Resource
import Retrofit.App.repository.MovieRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Result>>()
    val popularMovies: LiveData<List<Result>> = _popularMovies

    var message: String =""

    fun executeGetPopularMovies(page: Int) = viewModelScope.launch {
        when (val resource = repo.getPopularMovies(API_KEY, page)) {
            is Resource.Failed -> {
                message = resource.message
//                Log.i("Retrofit", "Error bro")

            }
            is Resource.Ok -> {
                message = resource.message
                _popularMovies.value = resource.data!!.results
//                Log.i("Retrofit", "${resource.data}")
            }
        }
    }
    //title,release date, poster path
}