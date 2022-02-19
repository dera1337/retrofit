package Retrofit.App.repository

import Retrofit.App.Model.PopularMovieResponse
import Retrofit.App.Util.Resource
import Retrofit.App.api.MovieAPI
import android.util.Log
import java.lang.Exception

class MovieRepositoryImpl(
    private val api: MovieAPI
) : MovieRepository {

    override suspend fun getPopularMovies(
        apiKey: String,
        page: Int
    ): Resource<PopularMovieResponse> {
        return try {
            val response = api.getPopularMovies(apiKey, page)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Ok(result, "Success get popular movies")
            } else {
                Resource.Failed("Unexpected error occured")
            }
        } catch (e: Exception) {
            Resource.Failed(e.message ?: "No internet connection")
        }
    }
}