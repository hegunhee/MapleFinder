package com.hegunhee.maplefinder.util

object SelectedDateFormatUtil {

    fun dateToApiFormat(year : Int, month : Int, dayOfMonth : Int) : String {
        val formatMonth = String.format("%02d",month)
        val formatDayOfMonth = String.format("%02d",dayOfMonth)
        return "${year}-${formatMonth}-${formatDayOfMonth}"
    }
}