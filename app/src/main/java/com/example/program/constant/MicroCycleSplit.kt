package com.example.program.constant

import android.os.Parcel
import android.os.Parcelable

enum class MicroCycleSplit(val count : Int, val text : String) {

    NO_SPLIT(1, "1일"),
    TWO_SPLIT(2, "2일"),
    THREE_SPLIT(3, "3일"),
    FOUR_SPLIT(4, "4일"),
    FIVE_SPLIT(5, "5일"),
    SIX_SPLIT(6, "6일"),
    SEVEN_SPLIT(7, "7일"),

}