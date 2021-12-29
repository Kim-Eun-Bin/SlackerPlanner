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

class MemoAdapter(private val dataSet: ArrayList<String>): RecyclerView.Adapter<MemoAdapter.ViewHolder>() {
    private lateinit var itemClickListener : OnItemClickListener

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val memoTv: TextView = view.findViewById(R.id.memo_tv)
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int) {
            val memoCheck: CheckBox = view.findViewById(R.id.memo_check)
            val memoTv: TextView = view.findViewById(R.id.memo_tv)

            memoCheck.toggle()

            if(memoCheck.isChecked) {
                memoTv.paintFlags = memoTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                memoTv.paintFlags = memoTv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.memo_cell, viewGroup, false)

        val memoTv: TextView = view.findViewById(R.id.memo_tv)
        val memoCheck: CheckBox = view.findViewById(R.id.memo_check)

        memoCheck.setOnClickListener{
            if(memoCheck.isChecked) {
                memoTv.paintFlags = memoTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                memoTv.paintFlags = memoTv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.memoTv.text = dataSet[position]

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount() = dataSet.size
}