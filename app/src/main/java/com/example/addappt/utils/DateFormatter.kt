package com.example.addappt.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun convertLongToDay(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}

fun convertLongToWeekRange(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp

    val startOfWeek = calendar.clone() as Calendar
    startOfWeek.set(Calendar.DAY_OF_WEEK, startOfWeek.firstDayOfWeek)

    val endOfWeek = calendar.clone() as Calendar
    endOfWeek.set(Calendar.DAY_OF_WEEK, endOfWeek.firstDayOfWeek + 6)

    val dateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
    return "${dateFormat.format(startOfWeek.time)} - ${dateFormat.format(endOfWeek.time)}"
}
fun convertLongToMonth(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}

fun formatScreenTime(totalScreenTime: Long): String {
    val days = TimeUnit.MILLISECONDS.toDays(totalScreenTime)
    val hours = TimeUnit.MILLISECONDS.toHours(totalScreenTime - TimeUnit.DAYS.toMillis(days))
    val minutes = TimeUnit.MILLISECONDS.toMinutes(totalScreenTime - TimeUnit.DAYS.toMillis(days) - TimeUnit.HOURS.toMillis(hours))

    return if (days > 0) {
        String.format("%d days, %02d:%02d hours", days, hours, minutes)
    } else {
        String.format("%02d:%02d hours", hours, minutes)
    }
}

fun formatScreenTimeForChart(totalScreenTimeAsFloat: Float): String {

    val totalScreenTime = totalScreenTimeAsFloat.toLong()

    val days = TimeUnit.MILLISECONDS.toDays(totalScreenTime)
    val hours = TimeUnit.MILLISECONDS.toHours(totalScreenTime - TimeUnit.DAYS.toMillis(days))
    val minutes = TimeUnit.MILLISECONDS.toMinutes(totalScreenTime - TimeUnit.DAYS.toMillis(days) - TimeUnit.HOURS.toMillis(hours))

    return String.format("%2d:%02d hrs", hours, minutes)
}

fun timestampAsRoundedHours(totalScreenTime: Long): Long {
    return TimeUnit.MILLISECONDS.toHours(totalScreenTime)
}