package com.hegunhee.maplefinder.util

import java.text.SimpleDateFormat

object TimeUtil {

    private val apiResponseFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mmXXX")
    private val uiFormat = SimpleDateFormat("yyyy년 MM월 dd일")

    fun apiFormatToUiFormat(apiTime : String) : String {
        val date = apiResponseFormat.parse(apiTime)
        return uiFormat.format(date)
    }
}