// This project is libre, and licenced under the terms of the
// DO WHAT THE FUCK YOU WANT TO PUBLIC LICENCE, version 3.1,
// as published by dtf on July 2019. See the COPYING file or
// https://ph.dtf.wtf/w/wtfpl/#version-3-1 for more details.

package com.woodblockwithoutco.rickroller.controller

import com.woodblockwithoutco.rickroller.track.RickRollTrackingInteractor
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.util.pipeline.PipelineContext
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.time.format.DateTimeFormatterBuilder
import java.util.*

/**
 * Handles call to /details.
 */
suspend fun PipelineContext<Unit, ApplicationCall>.handleDetailsCall(
    rickRollTrackingInteractor: RickRollTrackingInteractor,
    locale: Locale? = null
) {

    val formatterBuilder = DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .append(ISO_LOCAL_DATE)
        .appendLiteral(' ')
        .append(ISO_LOCAL_TIME)

    val formatter = if (locale != null) {
        formatterBuilder.toFormatter(locale)
    } else {
        formatterBuilder.toFormatter()
    }

    fun Long.toDurationPart(name: String): String {
        return takeIf { part -> part > 0 }?.let { part ->
            "$part $name "
        } ?: ""
    }

    val data = rickRollTrackingInteractor.getRickRolledData()
    call.respondText {
        data.asReversed().joinToString("\n\n") { item ->
            val duration = item.durationAgo
            val days = duration.toDaysPart()
            val hours = duration.toHoursPart()
            val minutes = duration.toMinutesPart()
            val seconds = duration.toSecondsPart()

            val dateTimeFormatted = item.time.format(formatter)

            val durationFormatted = (days.toDurationPart("days") +
                    hours.toLong().toDurationPart("hours") +
                    minutes.toLong().toDurationPart("minutes") +
                    seconds.toLong().toDurationPart("seconds")).trim()

            "* $dateTimeFormatted ($durationFormatted)"
        }
    }
}