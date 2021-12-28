package com.example.slackerplanner.memo

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R

class MemoAdapter(private val dataSet: ArrayList<String>): RecyclerView.Adapter<MemoAdapter.ViewHolder>() {
    var drawable: Drawable? = null
    private lateinit var itemClickListener : OnItemClickListener

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val memo_tv: TextView = view.findViewById(R.id.memo_tv)
        val memo_check: CheckBox = view.findViewById(R.id.memo_check)
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.memo_cell, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.memo_tv.text = dataSet[position]
//        holder.memo_check.isChecked = dataSet[position]

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount() = dataSet.size
}