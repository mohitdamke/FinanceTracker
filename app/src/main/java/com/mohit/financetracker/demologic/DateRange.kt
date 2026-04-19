package com.mohit.financetracker.demologic

import java.util.Calendar

data class DateRange(val start: Long, val end: Long)

fun getTodayRange(): DateRange {
    val start = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    val end = System.currentTimeMillis()
    return DateRange(start, end)
}

fun getMonthRange(): DateRange {
    val start = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    val end = System.currentTimeMillis()
    return DateRange(start, end)
}