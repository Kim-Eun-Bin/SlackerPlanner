package com.example.slackerplanner.Calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment() {
    lateinit var month_year_tv: TextView
    lateinit var calendarList: RecyclerView
    lateinit var today_btn: Button
    lateinit var smoothScroller: LinearSmoothScroller
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        calendarList = view.findViewById(R.id.calendarList)
        mLayoutManager = LinearLayoutManager(view.context)

        month_year_tv = view.findViewById(R.id.month_year_tv)
        today_btn = view.findViewById(R.id.today_btn)

        smoothScroller = object: LinearSmoothScroller(view.context) {
            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        // recyclerView orientation
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        calendarList.layoutManager = mLayoutManager

        // today
        CalendarUtils.selectedDate = LocalDate.now()
        setListView()
        setOnClickEvent()

        return view
    }

    fun setListView() {
        val list: ArrayList<Date> = arrayListOf()
        val lastDayOfMonth = Integer.parseInt(CalendarUtils.lastDayOfMonth(LocalDate.now()))

        for(i: Int in 1..lastDayOfMonth) {
            val date = LocalDate.of(LocalDate.now().year, LocalDate.now().month, i)
            val dayOfWeek: DayOfWeek = date.dayOfWeek
            dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US)

            list.add(Date(dayOfWeek.toString().substring(0, 3), i.toString()))
        }

        val adapter = CalendarAdapter(list)
        calendarList.adapter = adapter

        smoothScroller.targetPosition = CalendarUtils.selectedDate.dayOfMonth - 1
        mLayoutManager.startSmoothScroll(smoothScroller)
    }

    fun setOnClickEvent() {
        today_btn.setOnClickListener {
            smoothScroller.targetPosition = LocalDate.now().dayOfMonth - 1
            mLayoutManager.startSmoothScroll(smoothScroller)
            CalendarUtils.selectedDate = LocalDate.now()
        }
    }
}