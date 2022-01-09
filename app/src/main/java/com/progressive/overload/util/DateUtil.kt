package com.progressive.overload.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private const val outputPatternForProgramName = "yyyy년 MM월 dd일 HH시 mm분 ss초"
    private const val outputPatternForRecord = "yyyy년 MM월 dd일"
    private const val outputPatternForGraph = "MMdd"

//    private const val outputPatternForRecord = "yyyy년 MM월 dd일 HH시 mm분"
//    private const val outputPatternForGraph = "HHmm"

    fun getCurrentDateForProgramName(): String {
        val formmat = SimpleDateFormat(outputPatternForProgramName, Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        val result = formmat.format(date)

        return result
    }

    fun getCurrentDateForRecord(): String {
        val formmat = SimpleDateFormat(outputPatternForRecord, Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        val result = formmat.format(date)

        return result
    }

    fun getTimeMillisFromDate(date: String): Long {
        val dateFormat = SimpleDateFormat(outputPatternForRecord, Locale.getDefault())
        var date = dateFormat.parse(date)
        return date.time
    }

    fun getDateFromTimeMillis(timeMillis: Long, outputPattern: String): String {
        val dateFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
        val date = Date(timeMillis)

        return dateFormat.format(date)
    }

    fun getMonthDayFromDate(date: String): String {
        val timeMillis = getTimeMillisFromDate(date)
        return getDateFromTimeMillis(timeMillis, outputPatternForGraph)
    }
}