package com.jemutai.simplecalculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jemutai.simplecalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression

class MainActivity : AppCompatActivity() {

    private  lateinit var  binding:ActivityMainBinding
    var lastNumber = false
    var stateError = false
    var lastDot = false

    private lateinit var  expression:Expression
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun onClearClick(view: View) {}
    fun onBackSpace(view: View) {}
    fun onOperatorClick(view: View) {}
    fun onDigitClick(view: View) {}
    fun onClearAllClick(view: View) {}
}