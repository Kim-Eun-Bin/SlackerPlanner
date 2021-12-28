package com.example.slackerplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.slackerplanner.calendar.CalendarFragment
import com.example.slackerplanner.memo.MemoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val frame: RelativeLayout by lazy {
        findViewById(R.id.body_container)
    }
    private val bottomNagivationView: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(frame.id, CalendarFragment()).commit()

        bottomNagivationView.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.nav_calendar -> {
                    replaceFragment(CalendarFragment())
                    true
                }
                R.id.nav_chart -> {
                    replaceFragment(ChartFragment())
                    true
                }
                R.id.nav_memo -> {
                    replaceFragment(MemoFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }
}