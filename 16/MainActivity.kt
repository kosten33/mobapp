package com.example.film

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.film.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var moviesList: MutableList<Movie>
    private lateinit var displayedMovies: MutableList<Movie>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesList = loadMoviesFromJson() // Load movies from JSON file
        displayedMovies = mutableListOf()
        binding.randomMovieButton.setOnClickListener { showRandomMovie() }
        binding.resetButton.setOnClickListener { resetMovies() }
    }

    private fun loadMoviesFromJson(): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        val jsonFile = resources.openRawResource(R.raw.movies)
            .bufferedReader().use { it.readText() }

        val jsonArray = JSONObject(jsonFile).optJSONArray("movies")

        jsonArray?.let {
            for (i in 0 until it.length()) {
                val movieObj = it.getJSONObject(i)
                val title = movieObj.optString("title")
                val genre = parseGenre(movieObj.getJSONArray("genre"))
                val director = movieObj.optString("director")
                val releaseYear = movieObj.optInt("release_year")
                val rating = movieObj.optDouble("rating")
                val actors = parseActors(movieObj.getJSONArray("actors"))
                val description = movieObj.optString("description")

                val movie = Movie(title, genre, director, releaseYear, rating, actors, description)
                movies.add(movie)
            }
        }
        return movies
    }

    private fun parseGenre(jsonArray: JSONArray): List<String> {
        val genres = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            genres.add(jsonArray.getString(i))
        }
        return genres
    }

    private fun parseActors(jsonArray: JSONArray): List<String> {
        val actors = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            actors.add(jsonArray.getString(i))
        }
        return actors
    }

    private fun showRandomMovie() {
        if (displayedMovies.size == moviesList.size) {
            binding.movieDisplay.text = getString(R.string.allMoviesViewed)
        } else {
            val randomMovie = moviesList.random()
            displayedMovies.add(randomMovie)
            binding.movieDisplay.text = randomMovie.title // Display movie title or any other info
        }
    }

    private fun resetMovies() {
        displayedMovies.clear()
        binding.movieDisplay.text = ""
    }
}

data class Movie(
    val title: String,
    val genre: List<String>,
    val director: String,
    val releaseYear: Int,
    val rating: Double,
    val actors: List<String>,
    val description: String
)
