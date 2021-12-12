package com.example.program.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private const val outputPattern = "yyyy년 MM월 dd일 HH시 mm분 ss초"

    fun getCurrentDateForProgramName(): String? {
        val formmat = SimpleDateFormat(outputPattern, Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        val result = formmat.format(date)

        return result
    }

    fun getDateFromTimeMillis(timeMillis : Long?) : String {
        val currentDateFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

        Calendar.getInstance().apply {
            if (timeMillis != null) {
                timeInMillis = timeMillis
            }
            return currentDateFormat.format(this.time)
        }

    }
}