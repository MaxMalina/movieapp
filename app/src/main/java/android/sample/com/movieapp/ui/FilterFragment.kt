package android.sample.com.movieapp.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.view.Gravity
import android.widget.CheckBox

import androidx.fragment.app.Fragment

import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI

import android.sample.com.movieapp.model.Movie
import android.sample.com.movieapp.util.MovieUtils
import android.sample.com.movieapp.util.UIUtils
import kotlinx.android.synthetic.main.movie.view.*

class FilterFragment : Fragment() {

    private var movies: List<Movie>? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movies = it.getParcelableArrayList(MOVIES)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            relativeLayout {
                lparams(width = matchParent, height = matchParent)

                val yearsCheckBoxList:ArrayList<CheckBox> = ArrayList()
                val directorsCheckBoxList:ArrayList<CheckBox> = ArrayList()
                val genresCheckBoxList:ArrayList<CheckBox> = ArrayList()

                scrollView {
                    verticalLayout {
                        textView("Year") {
                            textColor = Color.BLACK
                            textSize = 20F
                        }

                        MovieUtils.getYearsList(movies).forEach{
                            yearsCheckBoxList.add(checkBox(it.toString()))
                        }

                        textView("Directors") {
                            textColor = Color.BLACK
                            textSize = 20F
                        }

                        MovieUtils.getDirectorsList(movies).forEach{
                            directorsCheckBoxList.add(checkBox(it))
                        }

                        textView("Genre") {
                            textColor = Color.BLACK
                            textSize = 20F
                        }

                        MovieUtils.getGenresList(movies).forEach{
                            genresCheckBoxList.add(checkBox(it))
                        }


                    }
                }.lparams(width = matchParent, height = matchParent)

                textView("Apply filter") {
                    textSize = 20F
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = Color.WHITE
                    backgroundColor = Color.parseColor("#C2185B")
                    setOnClickListener {
                        val checkedYears = UIUtils.getCheckedYears(yearsCheckBoxList)
                        val checkedDirectors = UIUtils.getCheckedDirectors(directorsCheckBoxList)
                        var checkedGenres = UIUtils.getCheckedGenres(genresCheckBoxList)

                        if(checkedYears.isEmpty()){
                            movies?.forEach{movie -> checkedYears.add(movie.year)}
                        }

                        if (checkedDirectors.isEmpty()){
                            movies?.forEach {movie -> checkedDirectors.add(movie.director)}
                        }


                        if (checkedGenres.isEmpty()) {
                            val resultList = ArrayList<String>()
                            movies?.forEach { movie ->
                                movie.genre.forEach {
                                    if (!resultList.contains(it)) {
                                        resultList.add(it)
                                    }
                                }
                            }
                            checkedGenres = resultList
                        }

                        onApplyPressed(checkedYears, checkedGenres, checkedDirectors)
                    }

                }.lparams (width = matchParent, height = 100) {
                    gravity = Gravity.BOTTOM or Gravity.END
                    alignParentBottom()
                }
            }
        }.view
    }

    fun onApplyPressed(years: List<Int>, genres: List<String>, directors: List<String>) {
        listener?.onFragmentInteraction(years, genres, directors)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnFragmentInteractionListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val MOVIES = "movies"

        fun newInstance(movies: ArrayList<Movie>) =
            FilterFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(MOVIES, movies)
                }
            }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(years: List<Int>, genres: List<String>, directors: List<String>)
    }
}
