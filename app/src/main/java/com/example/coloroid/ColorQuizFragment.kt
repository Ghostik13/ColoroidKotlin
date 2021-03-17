package com.example.coloroid

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.random.Random


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ColorQuiz : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color_quiz, container, false)
    }

    override fun onStart() {
        super.onStart()
        setBack()
    }

    private fun setBack() {
        val backgroundColor = view?.findViewById<View>(R.id.backColor)
        val randomColor = generateColor()
        backgroundColor?.setBackgroundColor(randomColor)
        var hex: String = Integer.toHexString(randomColor)
        hex = hex
            .replaceFirst("ff", "#")
            .toUpperCase(Locale.ROOT)
        initQuiz(hex)
    }

    private var counter = 0

    private var buttonLeft: Button?=null
    private var buttonRight: Button?=null

    private fun initQuiz(trueHex: String) {
        buttonLeft = view?.findViewById(R.id.button1)
        buttonRight = view?.findViewById(R.id.button2)
        val fakeColor = generateColor()
        val falseHex: String = Integer
            .toHexString(fakeColor)
            .replaceFirst("ff", "#")
            .toUpperCase(Locale.ROOT)
        val rnd = (0..1).random()
        if (rnd == 1) {
            buttonLeft?.text = trueHex
            buttonRight?.text = falseHex
        } else {
            buttonLeft?.text = falseHex
            buttonRight?.text = trueHex
        }
        val counterString = view?.findViewById<TextView>(R.id.colorHexView)
        counterString?.text = counter.toString()
        buttonLeft?.setOnClickListener {
            counterUpIfTrue(buttonLeft as Button, trueHex)
        }
        buttonRight?.setOnClickListener {
            counterUpIfTrue(buttonRight as Button, trueHex)
        }
    }

    private fun counterUpIfTrue(button: Button, trueHex: String) {
        if (button.text == trueHex)
            counter++
        else counter--
        setBack()
    }

    private fun generateColor(): Int {
        val r = Random.nextInt()
        val g = Random.nextInt()
        val b = Random.nextInt()
        return Color.rgb(r, g, b)
    }
}