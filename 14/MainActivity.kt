package com.example.tickets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tickets.databinding.ActivityMainBinding
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val citiesArray = resources.getStringArray(R.array.cities_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, citiesArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDeparture.adapter = adapter
        binding.spinnerArrival.adapter = adapter
    }
}
