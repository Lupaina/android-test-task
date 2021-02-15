package com.example.filmsapplication.util.extansions

import com.example.filmsapplication.util.DateFormats
import java.text.SimpleDateFormat
import java.util.*

fun String?.formatDate(pattern: DateFormats = DateFormats.DATE): String? {
    val date = this.convertToDate(pattern)
    return date.convertFromDate(pattern)
}


fun String?.convertToDate(pattern: DateFormats = DateFormats.DATE): Date? {
    return if (!this.isNullOrBlank()) {
        SimpleDateFormat(pattern.format, Locale.ROOT).parse(this)
    } else {
        null
    }
}

fun Date?.convertFromDate(pattern: DateFormats = DateFormats.DATE): String? {
    return if (this != null) {
        SimpleDateFormat(pattern.format, Locale.ROOT).format(this)
    } else {
        null
    }
}



