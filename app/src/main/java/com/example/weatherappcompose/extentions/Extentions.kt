package com.example.weatherappcompose.extentions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
    it.replaceFirstChar { char ->
        char.titlecase(
            Locale.getDefault()
        )
    }
}

fun Long.epochToDateTime(): String {

    val dateFormat = SimpleDateFormat("HH.mm", Locale.getDefault())
    val millis = TimeUnit.SECONDS.toMillis(this)
    val date = Date(millis)
    return dateFormat.format(date)

}