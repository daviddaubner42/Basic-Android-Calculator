package com.example.david.quickapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {

    var operator = 'x'
    var first = ArrayList<Double>(0)
    var f: Double = 0.0
    var second = ArrayList<Double>(0)
    var s: Double = 0.0

    var cur = 'f'

    var isit = false

    var answer: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "The Basic Calculator"

        val plusButton = findViewById<Button>(R.id.plus)
        plusButton.setOnClickListener { setOp('+') }
        val minusButton = findViewById<Button>(R.id.minus)
        minusButton.setOnClickListener { setOp('-') }
        val multiplyButton = findViewById<Button>(R.id.multiply)
        multiplyButton.setOnClickListener { setOp('*') }
        val divideButton = findViewById<Button>(R.id.divide)
        divideButton.setOnClickListener { setOp('/') }

        val one = findViewById<Button>(R.id.one)
        one.setOnClickListener { numberClick(1.0) }
        val two = findViewById<Button>(R.id.two)
        two.setOnClickListener { numberClick(2.0) }
        val three = findViewById<Button>(R.id.three)
        three.setOnClickListener { numberClick(3.0) }
        val four = findViewById<Button>(R.id.four)
        four.setOnClickListener { numberClick(4.0) }
        val five = findViewById<Button>(R.id.five)
        five.setOnClickListener { numberClick(5.0) }
        val six = findViewById<Button>(R.id.six)
        six.setOnClickListener { numberClick(6.0) }
        val seven = findViewById<Button>(R.id.seven)
        seven.setOnClickListener { numberClick(7.0) }
        val eight = findViewById<Button>(R.id.eight)
        eight.setOnClickListener { numberClick(8.0) }
        val nine = findViewById<Button>(R.id.nine)
        nine.setOnClickListener { numberClick(9.0) }
        val zero = findViewById<Button>(R.id.zero)
        zero.setOnClickListener { numberClick(0.0) }

        val ac = findViewById<Button>(R.id.ac)
        ac.setOnClickListener { annulate() }

        val equal = findViewById<Button>(R.id.equals)
        equal.setOnClickListener { finisher() }

        answer = findViewById<TextView>(R.id.answer)
    }

    protected fun annulate() {
        first.clear()
        second.clear()
        f = 0.0
        s = 0.0
        operator = 'x'
        cur = 'f'
        answer!!.text = "0"
        isit = false
    }

    protected fun displayAnswer(inp: Double) :String {
        if(inp - inp.toInt().toDouble() == 0.0) return inp.toInt().toString()
        else return inp.toString()
    }

    protected fun convert(inp: MutableList<Double>) : Double {
        var res = 0.0
        for(i in 0 .. inp.size-1) {
            res += inp[i] * pow(10.0,(inp.size-i-1).toDouble())
        }
        return res
    }

    protected fun setOp(inp: Char) {
        if(f != 0.0 && second.size > 0) {
            answer!!.text = displayAnswer(finisher())
            operator = inp
            return
        }
        if(cur == 'f') {
            cur = 's'
            operator = inp
            answer!!.text = operator.toString()
            f = convert(first)
        } else {
            operator = inp
            answer!!.text = operator.toString()
        }
    }

    protected fun numberClick(inp: Double) {
        if(isit && operator == 'x') {
            annulate()
            first.add(inp)
            answer!!.text = displayAnswer(convert(first))
            return
        }
        if(cur == 'f') {
            first.add(inp)
            answer!!.text = displayAnswer(convert(first))
        } else if(cur == 's') {
            second.add(inp)
            answer!!.text = displayAnswer(convert(second))
        }
    }

    protected fun finisher() :Double {
        s = convert(second)
        var res = 0.0
        if(operator == '+') {
            res = f+s
            answer!!.text = displayAnswer(res)
        } else if(operator == '-') {
            res = f-s
            answer!!.text = displayAnswer(res)
        } else if(operator == '*') {
            res = f*s
            answer!!.text = displayAnswer(res)
        } else if(operator == '/') {
            res = f/s
            answer!!.text = displayAnswer(res)
        }
        f = res
        second.clear()
        operator = 'x'
        isit = true
        return res
    }

}
