package com.kyawlinnthant.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DateTimeFormatter {
    fun formatDateString(inputDate: String, justDayOfWeek: Boolean = false): String {
        if (inputDate.isEmpty()) return ""
        try {
            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
            val parsedDate = Instant.from(formatter.parse(inputDate))

            val dayOfWeek = parsedDate.atZone(ZoneId.systemDefault()).dayOfWeek.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
            )
            val month = parsedDate.atZone(ZoneId.systemDefault()).month.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
            )
            val dayOfMonth = parsedDate.atZone(ZoneId.systemDefault()).dayOfMonth

            return if (justDayOfWeek) {
                dayOfWeek
            } else {
                "$dayOfWeek, $month $dayOfMonth"
            }
        } catch (e: Exception) {
            return ""
        }
    }
}
