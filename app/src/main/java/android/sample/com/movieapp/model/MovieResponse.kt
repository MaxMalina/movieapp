package android.sample.com.movieapp.model;

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("values")
    val results: List<Movie>
)
