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

/**
 * Handles call to /details.
 */
suspend fun PipelineContext<Unit, ApplicationCall>.handleDetailsCall(
    rickRollTrackingInteractor: RickRollTrackingInteractor
) {

    fun Number.toDurationPart(name: String): String {
        return takeIf { part -> part != 0 }?.let { part ->
            "$part $name "
        } ?: ""
    }

    val durations = rickRollTrackingInteractor.getTimesAgoRickRolled()
    call.respondText {
        durations.asReversed().joinToString("\n\n") { duration ->
            val days = duration.toDaysPart()
            val hours = duration.toDaysPart()
            val minutes = duration.toMinutesPart()
            val seconds = duration.toSecondsPart()

            ("* ${days.toDurationPart("days")}" +
                    hours.toDurationPart("hours") +
                    minutes.toDurationPart("minutes") +
                    seconds.toDurationPart("seconds")).trim()
        }
    }
}