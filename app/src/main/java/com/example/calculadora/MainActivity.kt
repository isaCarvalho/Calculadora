package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    lateinit var resultado : TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultado = findViewById<TextView>(R.id.txtResultado)
    }

    fun setNumber(v: View)
    {
        var text = (v as Button).text
        resultado.append(text)
    }

    fun calcular(v: View)
    {
        val str = resultado.text
        val regex = Regex("([\\d\\.]*)([+\\-\\*\\^\\/])([\\d\\.]*)")
        val matches = regex.findAll(str)

        matches.forEach { match -> resultado.text = operacao(match.groupValues[1].toDouble(), match.groupValues[2], match.groupValues[3].toDouble()).toString() }
    }

    fun operacao(n1 : Double, op : String, n2 : Double) : Double
    {
        when(op)
        {
            "+" -> return n1 + n2
            "-" -> return n1 - n2
            "*" -> return n1 * n2
            "/" -> return n1 / n2
            "^" -> return n1.pow(n2)
        }

        return 0.0
    }

    fun fatorial(v : View)
    {
        fun fat(n : Int) : Int
        {
            if (n == 1)
                return 1

            return n * fat(n-1)
        }

        val str = resultado.text
        val regex = Regex("(\\d+)")
        val matches = regex.findAll(str)

        matches.forEach { match -> resultado.text = fat(match.groupValues[1].toInt()).toString() }
    }

    fun limparTudo(v : View)
    {
        resultado.text = ""
    }

    fun limpar(v : View)
    {
        val comprimento = resultado.text.length

        if (comprimento <= 1)
            limparTudo(v)
        else
            resultado.text = resultado.text.removeRange(comprimento-2, comprimento-1)
    }

    fun realizarOp(f: (Double) -> Double)
    {
        val str = resultado.text
        val regex = Regex("(\\d+)")
        val matches = regex.findAll(str)

        matches.forEach { match -> resultado.text = f(match.groupValues[1].toDouble()).toString() }
    }

    fun raiz(v : View) = realizarOp { x : Double -> sqrt(x) }

    fun seno(v: View) = realizarOp { x : Double -> sin(x) }

    fun cosseno(v: View) = realizarOp { x : Double -> cos(x) }

    fun tangente(v: View) = realizarOp { x: Double -> tan(x) }

    fun exponencial(v: View) = realizarOp { x: Double -> exp(x) }

    fun ln(v: View) = realizarOp { x: Double -> ln(x) }

    fun log(v: View) = realizarOp { x: Double -> log(x, 10.0) }

    fun quadrado(v: View) = realizarOp { x: Double -> x * x }

    fun cubo(v: View) = realizarOp { x: Double -> x * x * x }
}

