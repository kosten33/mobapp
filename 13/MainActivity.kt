package com.example.film

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.film.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var moviesList: Array<String>
    private lateinit var displayedMovies: MutableList<String>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesList = resources.getStringArray(R.array.movies)
        displayedMovies = mutableListOf()
        binding.randomMovieButton.setOnClickListener { showRandomMovie() }
        binding.resetButton.setOnClickListener { resetMovies() }
    }

    private fun showRandomMovie() {
        if (displayedMovies.size == moviesList.size) {
            binding.movieDisplay.text = getString(R.string.allMoviesViewed)
        } else {
            var randomMovie: String
            do {
                randomMovie = moviesList.random()
            } while (displayedMovies.contains(randomMovie))
            displayedMovies.add(randomMovie)
            binding.movieDisplay.text = randomMovie
        }
    }

    private fun resetMovies() {
        displayedMovies.clear()
        binding.movieDisplay.text = ""
    }
}
