package com.hegunhee.maplefinder.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object SelectedDateFormatUtil {

    // 오전 1시 이후로 어제의 데이터 조회가 가능함
    private fun defaultDate() : LocalDateTime {
        return if(yesterdayDate.hour > 1){
            yesterdayDate
        }else{
            yesterdayDate.minusDays(1)
        }
    }

    fun defaultDateMills() : Long {
        return defaultDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    fun defaultDateString() : String{
        return defaultDate().format(DateFormat)
    }

    fun isSelectableDate(utcTimeMills: Long) : Boolean {
        val defaultDateMills = defaultDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val startDateMills = startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        return utcTimeMills in startDateMills..defaultDateMills
    }

    fun String.toTimeMills() : Long {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDate.parse(this, DateFormat).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    private val yesterdayDate = LocalDateTime.now().minusDays(1)
    private val startDate = LocalDateTime.of(2023,12,22,0,0)
    private val DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}