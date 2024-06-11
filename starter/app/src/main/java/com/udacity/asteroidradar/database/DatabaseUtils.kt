package com.udacity.asteroidradar.database

import com.udacity.asteroidradar.Constants
import java.text.SimpleDateFormat
import java.util.*

fun getTodayFormattedDate(): String {
    val currentTime = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(currentTime)
}

fun getLastDayOfWeekFormattedDate(): String {
    val calendar = Calendar.getInstance()
    calendar.time = Date()

    val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysUntilSunday = (Calendar.SATURDAY - currentDayOfWeek + 1) % 7

    calendar.add(Calendar.DAY_OF_YEAR, daysUntilSunday)

    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(calendar.time)
}