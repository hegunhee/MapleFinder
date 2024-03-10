package com.hegunhee.maplefinder.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object SelectedDateFormatUtil {

    fun dateToApiFormat(year : Int, month : Int, dayOfMonth : Int) : String {
        val formatMonth = String.format("%02d",month)
        val formatDayOfMonth = String.format("%02d",dayOfMonth)
        return "${year}-${formatMonth}-${formatDayOfMonth}"
    }

    // 오전 1시 이후로 어제의 데이터 조회가 가능함
    fun defaultDate() : String{
        val currentDate = LocalDateTime.now().minusDays(1)
        val currentHour = currentDate.hour
        return if(currentHour > 1) {
            currentDate.format(DateFormat)
        }else {
            currentDate.minusDays(1).format(DateFormat)
        }
    }

    private val DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}