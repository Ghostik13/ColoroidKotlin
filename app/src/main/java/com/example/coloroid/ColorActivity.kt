package com.example.coloroid

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ColorActivity : AppCompatActivity() {

    private lateinit var colorName: TextView
    private lateinit var backgroundColor: View
    private lateinit var hexView: TextView
    private lateinit var copyHexButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)
        init()
    }

    private fun init() {
        colorName = findViewById(R.id.colorNameView)
        backgroundColor = findViewById(R.id.backGround)
        hexView = findViewById(R.id.hexView)
        copyHexButton = findViewById(R.id.copyHexButton)

        var currentColorName: String? = null
        var currentColorBackground: Int? = null
        var currentHex: String? = null

        val bundle = intent.extras
        if (bundle != null) {
            currentColorName = bundle.getString(COLOR_NAME)
            currentColorBackground = bundle.getInt(COLOR_BACK)
            currentHex = bundle.getString(COLOR_HEX)
        }
        currentColorName?.let {
            colorName.text = it
        }
        currentColorBackground?.let {
            backgroundColor.setBackgroundResource(it)
        }
        currentHex?.let {
            hexView.text = it
            hexView.visibility = View.INVISIBLE
        }
        copyHexButton.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("CopiedText", hexView.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(
                this,
                R.string.toast_copy,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}