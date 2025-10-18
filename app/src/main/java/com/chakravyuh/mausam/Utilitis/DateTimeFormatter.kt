package com.chakravyuh.mausam.Utilitis

import com.chakravyuh.mausam.model.CurrentWeatherResponse
import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDate(weatherResponse: CurrentWeatherResponse?): String {
    if (weatherResponse == null) return ""
    val timestamp = weatherResponse.dt
    val timezoneOffset = weatherResponse.timezone
    val date = Date((timestamp + timezoneOffset) * 1000L)
    val formatter = SimpleDateFormat("EEEE, dd MMM, yyyy", Locale.getDefault())
    return formatter.format(date)
}



fun formatTimeTo12Hour(time: String): String {
    val inputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("h a", Locale.getDefault())

    val date = inputFormat.parse(time)
    return date?.let { outputFormat.format(it) } ?: "N/A"
}