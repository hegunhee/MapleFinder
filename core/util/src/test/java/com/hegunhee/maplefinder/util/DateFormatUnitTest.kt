package com.hegunhee.maplefinder.util

import org.junit.Test

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
}