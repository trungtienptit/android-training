package com.duyha.github.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun String.toDateTime(format: String): Date? {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return try {
        dateFormat.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.formatToString(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}