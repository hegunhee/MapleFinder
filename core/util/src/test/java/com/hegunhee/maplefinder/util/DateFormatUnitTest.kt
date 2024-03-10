package com.hegunhee.maplefinder.util

import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateFormatUnitTest {

    @Test
    fun `recent date if hour up 01 00`() {
        val currentDate = currentDate()
        val currentDateString = currentDate.format(format)
        if(SelectedDateFormatUtil.defaultDateString() == currentDateString) {
            print(currentDateString)
            assert(true)
        }else {
            assert(false)
        }
    }

    @Test
    fun `recent date if hour down 01 00`() {
        val currentDate = currentDate(2)
        val currentDateString = currentDate.format(format)
        println(currentDateString)
        assert(true)
    }

    // 가장 최근 조회가능날짜는 하루 전 (오전 1시 전은 이틀 전)
    private fun currentDate(days : Long = 1) =
        LocalDateTime.now().minusDays(days)
    private val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}