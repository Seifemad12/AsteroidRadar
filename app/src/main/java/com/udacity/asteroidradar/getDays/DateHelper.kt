package com.udacity.asteroidradar.getDays

import com.udacity.asteroidradar.Utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    fun getToday(): String {
        val mycalender = Calendar.getInstance()
        val currenttime = mycalender.time
//        val currenttime = LocalDateTime.now()
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(currenttime)
    }

    fun getWeekDate(): ArrayList<String> {
        val formattedDateList = ArrayList<String>()

        val calendar = Calendar.getInstance()
        for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
            val currentTime = calendar.time
            val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            formattedDateList.add(dateFormat.format(currentTime))
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
//        val s = formattedDateList[formattedDateList.size - 1]
        return formattedDateList
    }
}