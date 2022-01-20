package android.sample.com.movieapp.repository

import retrofit2.Call
import retrofit2.http.GET

import android.sample.com.movieapp.model.MovieResponse

interface MovieApi {
    @GET("movies")
    fun getMovies(): Call<MovieResponse>
}