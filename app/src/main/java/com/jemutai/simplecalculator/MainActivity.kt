package com.jemutai.simplecalculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jemutai.simplecalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.log

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

    fun onClearClick(view: View) {
        binding.dataTv.text = ""
        lastNumber = false
    }
    fun onBackSpace(view: View) {
        binding.dataTv.text = binding.dataTv.text.dropLast(1)
        try {
             val  lastNo = binding.dataTv.text.last()

            if (lastNo.isDigit()){
                onEqual()
            }
        }
        catch (e:Exception){
            binding.resultTv.text = ""
            binding.resultTv.visibility = View.GONE
            Log.e("last number error", e.toString())
        }
    }
    fun onOperatorClick(view: View) {

        if (!stateError && lastNumber){
            binding.dataTv.append((view as Button).text)
            lastDot = false
            lastNumber = false
            onEqual()
        }
    }
    fun onDigitClick(view: View) {

        if (stateError){
           binding.dataTv.text = (view as  Button).text
            stateError = false
        }
        else{
            binding.dataTv.append((view as Button).text)

        }
        lastNumber = false
        onEqual()

    }
    fun onClearAllClick(view: View) {

        binding.dataTv.text =""
        binding.resultTv.text = ""
        stateError = false
        lastDot = false
        lastNumber = false
        binding.resultTv.visibility = View.GONE
    }

    fun onEqual(){
        if (lastNumber && !stateError){
            val text =binding.dataTv.text.toString()

            expression =ExpressionBuilder(text).build()

            try {
                val  result = expression.evaluate()
                binding.resultTv.visibility = View.VISIBLE
                binding.resultTv.text ="=" +result.toString()
            }
            catch (ex:ArithmeticException){
                Log.e("evaluate error",ex.toString() )

                binding.resultTv.text = "Error"
                stateError= true
                lastNumber= false

            }
        }

    }

    fun onEqualClick(view: View) {
        onEqual()
        binding.dataTv.text = binding.resultTv.text.toString().drop(1)

    }
}