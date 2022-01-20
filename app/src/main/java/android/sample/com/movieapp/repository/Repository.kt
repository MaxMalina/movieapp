package android.sample.com.movieapp.repository

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import android.sample.com.movieapp.model.Movie
import android.sample.com.movieapp.model.MovieResponse

class Repository {
    private val TAG = Repository::class.java.simpleName

    private val api: MovieApi
    private val retrofit:Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(MovieApi::class.java)
    }

    companion object {
        private const val BASE_URL : String = "https://demo0216585.mockable.io/"
        private const val NETWORK_ERROR_MESSAGE = "Network error occurred!"

        private val INSTANCE: Repository = Repository()

        fun getInstance() : Repository {
            return INSTANCE
        }
    }

    fun fetchMovies(): LiveData<List<Movie>> {
        val call = api.getMovies()
        val data = MutableLiveData<List<Movie>>()

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.body() != null)
                    data.postValue(response.body()!!.results)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, NETWORK_ERROR_MESSAGE + t.message)
            }
        })

        return data
    }
}