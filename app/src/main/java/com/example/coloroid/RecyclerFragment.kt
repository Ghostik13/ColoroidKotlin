package com.example.coloroid

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

const val COLOR_NAME = "param1"
const val COLOR_BACK = "param2"
const val COLOR_HEX = "param3"

class RecyclerFragment : Fragment() {

    private val colors = mutableListOf(
        Color(R.color.ultra, "Ultra", "#BB86FC"),
        Color(R.color.red, "Red", "#C62828"),
        Color(R.color.cerise, "Cerise", "#AD1457"),
        Color(R.color.fuchia, "Fuchia", "#C800FF"),
        Color(R.color.purple, "Purple", "#6A1B9A"),
        Color(R.color.violet, "Violet", "#4527A0"),
        Color(R.color.ultramarine, "Ultramarine", "#283593"),
        Color(R.color.skyblue, "Skyblue", "#1565C0"),
        Color(R.color.azure, "Azure", "#0277BD"),
        Color(R.color.seagreen, "Seagreen", "#00838F"),
        Color(R.color.jade, "Jade", "#00695C"),
        Color(R.color.forestgreen, "Forestgreen", "#2E7D32"),
        Color(R.color.green, "Green", "#FD558B2F"),
        Color(R.color.gold, "Gold", "#9E9D24"),
        Color(R.color.yellow, "Yellow", "#FFE600"),
        Color(R.color.goldenrod, "Goldenrod", "#F9A825"),
        Color(R.color.apricot, "Apricot", "#FF8F00"),
        Color(R.color.orange, "Orange", "#EF6C00"),
        Color(R.color.redorange, "Redorange", "#D84315"),
        Color(R.color.gray, "Gray", "#555555")
    )

    private lateinit var recycler: RecyclerView
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
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)
        initRecycler(view)
        return view
    }

    private fun initRecycler(view: View) {
        recycler = view.findViewById(R.id.recyclerView)
        val adapter = ColorAdapter(colors){color ->
            val intent = Intent(this.context, ColorActivity::class.java)
            intent.putExtra(COLOR_NAME, color.name)
            intent.putExtra(COLOR_BACK, color.color)
            intent.putExtra(COLOR_HEX, color.hex)
            startActivity(intent)
        }
        recycler.adapter = adapter
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recycler.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }
}