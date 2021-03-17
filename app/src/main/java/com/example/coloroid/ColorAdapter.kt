package com.example.coloroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(
        private val colors: MutableList<Color>,
        private val onClick: (Color) -> Unit):
        RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.row_color, parent,false)
        return ColorViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    class ColorViewHolder(view: View, val onClick: (Color) -> Unit): RecyclerView.ViewHolder(view) {
        private val colorName: TextView = view.findViewById(R.id.colorName)
        private val viewHolder: View = view.findViewById(R.id.row_viewHolder)
        private var currentColor: Color? = null

        init {
            view.setOnClickListener {
                currentColor?.let {
                    onClick(it)
                }
            }
        }

        fun bind(color: Color){
            currentColor=color
            viewHolder.setBackgroundResource(color.color)
            colorName.text = color.name
        }
    }
}