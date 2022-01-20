package android.sample.com.movieapp.ui

import android.os.Bundle
import android.sample.com.movieapp.R

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.LinearLayoutManager

import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.sample.com.movieapp.viewmodel.MovieViewModel
import android.sample.com.movieapp.databinding.MainActivityBinding
import android.sample.com.movieapp.model.Movie

class MainActivity : AppCompatActivity(), FilterFragment.OnFragmentInteractionListener {
    private var viewModel: MovieViewModel? = null
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        showRecyclerView()

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel!!.fetchMovies().observe(this, Observer {
            binding.recyclerView.layoutManager = LinearLayoutManager(this.applicationContext)
            binding.recyclerView.adapter = MovieAdapter(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter -> {
            hideRecyclerView()

            supportFragmentManager.beginTransaction()
                .addToBackStack("FILTER")
                .replace(R.id.fragmentContainer, FilterFragment.newInstance(viewModel!!.getMovieList().value as ArrayList<Movie>))
                .commit()
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onFragmentInteraction(years: List<Int>, genres: List<String>, directors: List<String>) {
        supportFragmentManager.popBackStack()
        showRecyclerView()

        val adapter = (binding.recyclerView.adapter as MovieAdapter)
        adapter.filterByYear(years)
        adapter.filterByDirector(directors)
        adapter.filterByGenre(genres)
        adapter.notifyDataSetChanged()
    }

    private fun showRecyclerView() {
        binding.fragmentContainer.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun hideRecyclerView() {
        binding.fragmentContainer.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }
}
