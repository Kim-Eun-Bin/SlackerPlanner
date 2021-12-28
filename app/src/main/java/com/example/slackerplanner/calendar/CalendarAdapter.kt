package com.example.slackerplanner.calendar

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R

class CalendarAdapter(private val dataSet: ArrayList<Date>): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    var drawable: Drawable? = null
    private lateinit var itemClickListener : OnItemClickListener

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val date_tv: TextView = view.findViewById(R.id.date_cell)
        val day_tv: TextView = view.findViewById(R.id.day_cell)
    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.calendar_cell, viewGroup, false)

        drawable = ContextCompat.getDrawable(view.context, R.drawable.round)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date_tv.text = dataSet[position].date
        holder.day_tv.text = dataSet[position].day

        if(dataSet[position].date == CalendarUtils.selectedDate.dayOfMonth.toString()) {
            holder.date_tv.background = drawable
        } else {
            holder.date_tv.background = null
        }

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount() = dataSet.size
}