package android.sample.com.movieapp.util

import android.sample.com.movieapp.model.Movie

class MovieUtils {

    companion object {

        fun getYearsList(movies: List<Movie>?): ArrayList<Int> {
            val yearList = ArrayList<Int>()
            movies?.forEach { movie ->
                if (!yearList.contains(movie.year)) {
                    yearList.add(movie.year)
                }

            }

            yearList.sort()
            return yearList
        }

        fun getDirectorsList(movies: List<Movie>?): ArrayList<String> {
            val directorsList = ArrayList<String>()
            movies?.forEach { movie ->
                if (!directorsList.contains(movie.director)) {
                    directorsList.add(movie.director)
                }
            }

            directorsList.sort()
            return directorsList
        }

        fun getGenresList(movies: List<Movie>?): ArrayList<String> {
            val genresList = ArrayList<String>()
            movies?.forEach {
                    movie -> movie.genre.forEach {
                        genre -> if (!genresList.contains(genre)) {
                            genresList.add(genre)
                        }
                    }
            }

            genresList.sort()
            return genresList
        }
    }
}