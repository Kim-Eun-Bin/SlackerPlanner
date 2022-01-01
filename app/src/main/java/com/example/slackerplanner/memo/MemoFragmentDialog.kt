package com.example.slackerplanner.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.slackerplanner.R

class MemoFragmentDialog: DialogFragment() {
    private lateinit var saveBtn: Button
    private lateinit var cancelBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val view: View = inflater.inflate(R.layout.memo_dialog, container, false)

        saveBtn = view.findViewById(R.id.save_btn)
        cancelBtn = view.findViewById(R.id.cancel_btn)

        saveBtn.setOnClickListener{
            Toast.makeText(view.context, "save", Toast.LENGTH_LONG).show()
            dismiss()
        }

        cancelBtn.setOnClickListener{
            dismiss()
        }

        return view
    }
}