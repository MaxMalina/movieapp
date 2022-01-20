package android.sample.com.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso

import android.sample.com.movieapp.model.Movie
import android.sample.com.movieapp.databinding.MovieBinding

class MovieAdapter (private var mDataList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val filmBinding = MovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(filmBinding)
    }

    fun filterByYear(years:List<Int>) { mDataList = mDataList.filter { movie -> years.contains(movie.year) } }

    fun filterByDirector(directors:List<String>) {
        mDataList = mDataList.filter { movie -> directors.contains(movie.director) }
    }

    fun filterByGenre(genres:List<String>) {
        val resultList = ArrayList<Movie>()
        genres.forEach {
                genre -> mDataList.forEach {
                    movie -> if(movie.genre.contains(genre) && !resultList.contains(movie)) {
                        resultList.add(movie)
                }
            }
        }

        mDataList = resultList
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var genre = ""
        mDataList[position].genre.forEach {
            genre += if (mDataList[position].genre.indexOf(it) != mDataList[position].genre.lastIndex)
                "$it, "
            else
                it
        }

        Picasso.get()
            .load(mDataList[position].imageUrl)
            .into(holder.binding.image)

        holder.binding.title.text = mDataList[position].title
        holder.binding.year.text = mDataList[position].year.toString()
        holder.binding.director.text = mDataList[position].director
        holder.binding.description.text = mDataList[position].description
        holder.binding.genre.text = genre
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class MyViewHolder(val binding: MovieBinding) : RecyclerView.ViewHolder(binding.root)
}