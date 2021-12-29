package com.example.slackerplanner.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R

class MemoFragment : Fragment() {
    private val itemList = arrayListOf<String>()
    private val listAdapter = MemoAdapter(itemList)
    private lateinit var memoList: RecyclerView
    private lateinit var addBtn: Button
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_memo, container, false)

        mLayoutManager = LinearLayoutManager(view.context)
        memoList = view.findViewById(R.id.memo_list)
        addBtn = view.findViewById(R.id.add_memo_btn)

        // recyclerView orientation
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        memoList.layoutManager = mLayoutManager

        setListView()
        setOnClickEvent()

        return view
    }

    private fun setListView() {
        itemList.add("exercise")
        itemList.add("exercise")
        itemList.add("exercise")
        itemList.add("exercise")
        itemList.add("exercise")

        memoList.adapter = listAdapter
        // TODO
    }

    private fun setOnClickEvent() {
        listAdapter.setItemClickListener(object: MemoAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                super.onClick(view, position)
                listAdapter.notifyDataSetChanged()
            }
        })

        addBtn.setOnClickListener {
            // TODO
        }
    }
}