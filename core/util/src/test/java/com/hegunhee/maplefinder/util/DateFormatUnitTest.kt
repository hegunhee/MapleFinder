package com.hegunhee.maplefinder.util

import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateFormatUnitTest {

    @Test
    fun `date format test (2024-01-01)`() {
        val year = 2024
        val month = 1
        val dayOfMonth = 1
        val correctDateFormat = "2024-01-01"
        val formatDate = SelectedDateFormatUtil.dateToApiFormat(year,month,dayOfMonth)
        println(formatDate)
        if(correctDateFormat == formatDate) {
            assert(true)
        }else {
            assert(false)
        }
    }

    @Test
    fun `date format test (2024-01-12)`() {
        val year = 2024
        val month = 1
        val dayOfMonth = 12
        val correctDateFormat = "2024-01-12"
        val formatDate = SelectedDateFormatUtil.dateToApiFormat(year,month,dayOfMonth)
        println(formatDate)
        if(correctDateFormat == formatDate) {
            assert(true)
        }else {
            assert(false)
        }
    }

    @Test
    fun `recent date if hour up 01 00`() {
        val currentDate = currentDate()
        val currentDateString = currentDate.format(format)
        if(SelectedDateFormatUtil.defaultDate() == currentDateString) {
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