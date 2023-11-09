package com.kyawlinnthant.util

import com.google.common.truth.Truth
import org.junit.Test

class DateTimeFormatterTest {

    @Test
    fun `empty date string returns empty`() {
        val result = DateTimeFormatter.formatDateString("")
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `wrong format date string returns empty`() {
        val result = DateTimeFormatter.formatDateString("12:222:007")
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `correct format date string returns desired dayOfWeek, month dayOfMonth`() {
        val result = DateTimeFormatter.formatDateString("2017-01-23T07:48:12.991Z")
        Truth.assertThat(result).isEqualTo("Monday, January 23")
    }

    @Test
    fun `correct format date string returns desired dayOfWeek if specified`() {
        val result = DateTimeFormatter.formatDateString(inputDate = "2017-01-23T07:48:12.991Z", justDayOfWeek = true)
        Truth.assertThat(result).isEqualTo("Monday")
    }
}
