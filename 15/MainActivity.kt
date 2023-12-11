package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPlayGameClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)

        val startEditText = findViewById<EditText>(R.id.start)
        val finishEditText = findViewById<EditText>(R.id.finish)

        val start = startEditText.text.toString().toIntOrNull() ?: 0
        val finish = finishEditText.text.toString().toIntOrNull() ?: 100

        intent.putExtra("start", start)
        intent.putExtra("finish", finish)
        startActivity(intent)
    }
}
