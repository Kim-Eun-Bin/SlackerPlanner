package com.example.slackerplanner.calendar

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

object CalendarUtils {
    lateinit var selectedDate: LocalDate

    fun fomattedDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM yy")
        return date.format(formatter)
    }

    fun lastDayOfMonth(date: LocalDate): String {
        val localDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
        return localDate.format(DateTimeFormatter.ofPattern("dd"))
    }
}