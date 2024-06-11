package com.udacity.asteroidradar.database

import com.udacity.asteroidradar.Constants
import java.text.SimpleDateFormat
import java.util.*

fun getTodayFormattedDate(): String {
    val currentTime = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(currentTime)
}