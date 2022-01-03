package com.example.slackerplanner.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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

class CalendarFragment : Fragment() {
    val itemList = arrayListOf<Date>()
    val listAdapter = CalendarAdapter(itemList)
    private lateinit var monthYearTv: TextView
    lateinit var calendarList: RecyclerView
    private lateinit var todayBtn: Button
    private lateinit var addBtn: Button
    lateinit var smoothScroller: LinearSmoothScroller
    lateinit var mLayoutManager: LinearLayoutManager
    val calendarFragmentDialog: CalendarFragmentDialog = CalendarFragmentDialog()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        calendarList = view.findViewById(R.id.calendar_list)
        mLayoutManager = LinearLayoutManager(view.context)

        monthYearTv = view.findViewById(R.id.month_year_tv)
        todayBtn = view.findViewById(R.id.today_btn)
        addBtn = view.findViewById(R.id.add_btn)

        smoothScroller = object: LinearSmoothScroller(view.context) {
            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }

        listAdapter.setItemClickListener(object: CalendarAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                CalendarUtils.selectedDate = LocalDate.of(LocalDate.now().year, LocalDate.now().month, Integer.parseInt(itemList[position].date))
                listAdapter.notifyDataSetChanged()
                smoothScroller.targetPosition = CalendarUtils.selectedDate.dayOfMonth - 1
                mLayoutManager.startSmoothScroll(smoothScroller)
            }
        })

        // recyclerView orientation
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        calendarList.layoutManager = mLayoutManager

        // today
        CalendarUtils.selectedDate = LocalDate.now()
        setListView()
        setOnClickEvent()

        return view
    }

    private fun setListView() {
        val lastDayOfMonth = Integer.parseInt(CalendarUtils.lastDayOfMonth(LocalDate.now()))

        for(i: Int in 1..lastDayOfMonth) {
            val date = LocalDate.of(LocalDate.now().year, LocalDate.now().month, i)
            val dayOfWeek: DayOfWeek = date.dayOfWeek
            dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US)

            itemList.add(Date(dayOfWeek.toString().substring(0, 3), i.toString()))
        }
        calendarList.adapter = listAdapter

        smoothScroller.targetPosition = CalendarUtils.selectedDate.dayOfMonth - 1
        mLayoutManager.startSmoothScroll(smoothScroller)
    }

    private fun setOnClickEvent() {
        todayBtn.setOnClickListener {
            smoothScroller.targetPosition = LocalDate.now().dayOfMonth - 1
            mLayoutManager.startSmoothScroll(smoothScroller)
            CalendarUtils.selectedDate = LocalDate.now()
        }

        addBtn.setOnClickListener{
            calendarFragmentDialog.show(requireActivity().supportFragmentManager, "calendar dialog")
        }
    }
}