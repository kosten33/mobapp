package com.example.num

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.num.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Использование лямбда-выражения для установки слушателя на кнопку
        binding.buttonCount.setOnClickListener {
            calculateSum()
        }
    }

    private fun calculateSum() {
        val fVal = binding.firstNum.text.toString()
        val sVal = binding.secondNum.text.toString()

        if (fVal.isEmpty() || sVal.isEmpty()) {
            showToast("Введите числа")
        } else {
            try {
                val fNum = fVal.toFloat()
                val sNum = sVal.toFloat()
                val sum = fNum + sNum
                binding.result.text = sum.toString()
            } catch (e: NumberFormatException) {
                showToast("Ошибка ввода")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
