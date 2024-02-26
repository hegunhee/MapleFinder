package com.hegunhee.maplefinder.data

import org.junit.Test
import java.text.DecimalFormat

class StringToIntTest {

    @Test
    fun `string to Int if type float`() {
        val floatString = "8.0"
        if(floatString.toIntOrNull() == null) {
            println("type cast failure")
            assert(true)
        }else {
            println("type cast true")
            assert(false)
        }
    }

    @Test
    fun `string filter percent or int value`() {
        val list = listOf<String>(
            "8.5",
            "3",
            "30.0",
            "3000"
        )
        val filterList = list.map {
            if(it.contains('.')) {
                "${it.split('.')[0]}%"
            }else {
                val format = DecimalFormat("#,###")
                format.format(it.toInt())
            }
        }
        println(filterList)
        assert(filterList == actualFilterResult)
    }

    companion object {
        val actualFilterResult = listOf<String>(
            "8%",
            "3",
            "30%",
            "3,000"
        )
    }
}