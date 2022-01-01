package com.example.slackerplanner.memo

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R
import java.time.LocalDate

class MemoFragment : Fragment() {
    private val itemList = arrayListOf<Memo>()
    private val listAdapter = MemoAdapter(itemList)
    private lateinit var memoList: RecyclerView
    private lateinit var addBtn: Button
    lateinit var mLayoutManager: LinearLayoutManager
    private val memoFragmentDialog: MemoFragmentDialog = MemoFragmentDialog()

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
        setOnClickEvent(view)

        return view
    }

    private fun setListView() {
        val memo = Memo("exercise",false)
        itemList.add(memo)
        itemList.add(memo)
        itemList.add(memo)
        itemList.add(memo)
        itemList.add(memo)

        memoList.adapter = listAdapter
        // TODO
    }

    private fun setOnClickEvent(view: View) {
        listAdapter.setItemClickListener(object: MemoAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                super.onClick(view, position)
                Toast.makeText(context, "toast", Toast.LENGTH_LONG).show()
                listAdapter.notifyDataSetChanged()
            }
        })

        addBtn.setOnClickListener{
            memoFragmentDialog.show(requireActivity().supportFragmentManager, "memo dialog")
        }
    }
}