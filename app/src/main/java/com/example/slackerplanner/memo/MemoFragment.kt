package com.example.slackerplanner.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R
import com.example.slackerplanner.calendar.CalendarAdapter
import com.example.slackerplanner.calendar.CalendarUtils
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class MemoFragment : Fragment() {
    val itemList = arrayListOf<String>()
    val listAdapter = MemoAdapter(itemList)
    lateinit var memoList: RecyclerView
    lateinit var add_btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_memo, container, false)
        memoList = view.findViewById(R.id.memo_list)
        add_btn = view.findViewById(R.id.add_memo_btn)

        listAdapter.setItemClickListener(object: MemoAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                // TODO
                listAdapter.notifyDataSetChanged()
            }
        })

        setListView()
        setOnClickEvent()

        return view
    }

    fun setListView() {
//        itemList.add()
        memoList.adapter = listAdapter
        // TODO
    }

    fun setOnClickEvent() {
        add_btn.setOnClickListener {
            // TODO
        }
    }
}