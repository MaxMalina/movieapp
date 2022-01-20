package android.sample.com.movieapp.viewmodel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

import android.sample.com.movieapp.model.Movie
import android.sample.com.movieapp.repository.Repository

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : Repository = Repository.getInstance()
    private var movieList : MediatorLiveData<List<Movie>> = MediatorLiveData()

    fun getMovieList() : LiveData<List<Movie>> {
        return movieList
    }

    fun fetchMovies() : LiveData<List<Movie>> {
        movieList.addSource(repository.fetchMovies()) { data -> movieList.setValue(data) }
        return movieList
    }
}
