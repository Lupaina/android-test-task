package com.example.filmsapplication.util

import java.util.*

object DateUtils {

    fun isSameMonth(date1: Date?, date2: Date?): Boolean {
        if (date1 == null || date2 == null) return false
        val cal1 = Calendar.getInstance().apply { time = date1 }
        val cal2 = Calendar.getInstance().apply { time = date2 }
        return isSameMonth(cal1, cal2)
    }

    fun isSameMonth(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1[Calendar.ERA] == cal2[Calendar.ERA] &&
                cal1[Calendar.YEAR] == cal2[Calendar.YEAR] &&
                cal1[Calendar.MONTH] == cal2[Calendar.MONTH]
    }
}