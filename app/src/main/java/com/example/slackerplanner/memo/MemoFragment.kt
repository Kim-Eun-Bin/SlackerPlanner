package com.example.slackerplanner.memo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.slackerplanner.R

class MemoFragment : Fragment() {
    private val listAdapter = MemoAdapter(MemoList.memoList)
    private lateinit var memoList: RecyclerView
    private lateinit var addBtn: Button
    lateinit var mLayoutManager: LinearLayoutManager
    private val memoDialogFragment: MemoFragmentDialog = MemoFragmentDialog()

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
        val sharedPref = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val temp: String? = sharedPref?.getString("content", "")

        if (temp != null) {
            MemoList.memoList.add(temp)
        }

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
            memoDialogFragment.show(requireActivity().supportFragmentManager, "memo dialog")
        }
    }
}