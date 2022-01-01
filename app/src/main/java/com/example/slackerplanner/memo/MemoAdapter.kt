package com.example.slackerplanner.memo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R

class MemoAdapter(private val dataSet: ArrayList<Memo>): RecyclerView.Adapter<MemoAdapter.ViewHolder>() {
    private lateinit var itemClickListener : OnItemClickListener

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val memoTv: TextView = view.findViewById(R.id.memo_tv)
        val memoCheck: CheckBox = view.findViewById(R.id.memo_check)
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int) {
            val memoCheck: CheckBox = view.findViewById(R.id.memo_check)
            memoCheck.isChecked = true
        }
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.memo_cell, viewGroup, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.memoTv.text = dataSet[position].content
        holder.memoCheck.isChecked = dataSet[position].check

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

        if(dataSet[position].check) {
            holder.memoTv.paintFlags = holder.memoCheck.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount() = dataSet.size
}