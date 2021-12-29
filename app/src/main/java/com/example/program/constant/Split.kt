package com.example.program.constant

import android.os.Parcel
import android.os.Parcelable

enum class Split(val count : Int, val text : String?) {

    NO_SPLIT(1, "무분할"),
    TWO_SPLIT(2, "2분할"),
    THREE_SPLIT(3, "3분할"),
    FOUR_SPLIT(4, "4분할"),
    FIVE_SPLIT(5, "5분할"),
    SIX_SPLIT(6, "6분할"),
    SEVEN_SPLIT(7, "7분할"),
    EIGHT_SPLIT(8, "8분할"),
    NINE_SPLIT(9, "9분할"),
    TEN_SPLIT(10, "10분할"),

}