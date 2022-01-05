package com.example.slackerplanner.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.slackerplanner.R

class CalendarFragmentDialog: DialogFragment() {
    private lateinit var saveBtn: Button
    private lateinit var cancelBtn: Button
    private lateinit var contentEt: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val view: View = inflater.inflate(R.layout.calendar_dialog, container, false)

        saveBtn = view.findViewById(R.id.calendar_save_btn)
        cancelBtn = view.findViewById(R.id.calendar_cancel_btn)
        contentEt = view.findViewById(R.id.calendar_content_et)

        saveBtn.setOnClickListener{
            TodoList.todoList.add(contentEt.text.toString())
            contentEt.text = null
            Toast.makeText(view.context, "save", Toast.LENGTH_LONG).show()
            dismiss()
        }

        cancelBtn.setOnClickListener{
            dismiss()
        }

        return view
    }
}