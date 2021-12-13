package com.example.program.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private const val outputPatternForProgramName = "yyyy년 MM월 dd일 HH시 mm분 ss초"
    private const val outputPatternForRecord = "yyyy년 MM월 dd일"

    fun getCurrentDateForProgramName(): String? {
        val formmat = SimpleDateFormat(outputPatternForProgramName, Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        val result = formmat.format(date)

        return result
    }

    fun getCurrentDateForRecord(): String? {
        val formmat = SimpleDateFormat(outputPatternForRecord, Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        val result = formmat.format(date)

        return result
    }

    fun getDateFromTimeMillis(timeMillis : Long?) : String {
        val currentDateFormat = SimpleDateFormat(outputPatternForProgramName, Locale.getDefault())

        Calendar.getInstance().apply {
            if (timeMillis != null) {
                timeInMillis = timeMillis
            }
            return currentDateFormat.format(this.time)
        }

    }
}