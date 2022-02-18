package Retrofit.App.repository

import Retrofit.App.Model.PopularMovieResponse
import Retrofit.App.Util.Resource
import retrofit2.Response
import retrofit2.http.Query

interface MovieRepository {

    suspend fun getPopularMovies(
        apiKey: String,
        page: Int
    ): Resource<PopularMovieResponse>
}