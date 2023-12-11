package com.example.color_tiles2

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Coord(val x: Int, val y: Int)

class MainActivity : AppCompatActivity() {

    lateinit var tiles: Array<Array<View>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tiles = arrayOf(
            arrayOf(findViewById(R.id.t00), findViewById(R.id.t01), findViewById(R.id.t02), findViewById(R.id.t03)),
            arrayOf(findViewById(R.id.t10), findViewById(R.id.t11), findViewById(R.id.t12), findViewById(R.id.t13)),
            arrayOf(findViewById(R.id.t20), findViewById(R.id.t21), findViewById(R.id.t22), findViewById(R.id.t23)),
            arrayOf(findViewById(R.id.t30), findViewById(R.id.t31), findViewById(R.id.t32), findViewById(R.id.t33))
        )

        initField()
    }

    fun getCoordFromString(s: String): Coord {
        val x = s[0].toString().toInt()
        val y = s[1].toString().toInt()
        return Coord(x, y)
    }

    fun changeColor(view: View) {
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)
        val drawable = view.background as ColorDrawable
        view.setBackgroundColor(if (drawable.color == brightColor) darkColor else brightColor)
    }

    fun onClick(v: View) {
        val coord = getCoordFromString(v.getTag().toString())
        changeColor(v)

        for (i in 0 until 4) {
            changeColor(tiles[coord.x][i])
            changeColor(tiles[i][coord.y])
        }

        if (checkVictory()) {
            Toast.makeText(this, "Поздравляем! Вы выиграли!", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkVictory(): Boolean {
        val firstColor = (tiles[0][0].background as ColorDrawable).color

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val currentColor = (tiles[i][j].background as ColorDrawable).color
                if (currentColor != firstColor) {
                    return false
                }
            }
        }
        return true
    }

    fun initField() {
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val randomColor = (0..1).random()
                val color = if (randomColor == 0) darkColor else brightColor
                tiles[i][j].setBackgroundColor(color)
            }
        }
    }
}
