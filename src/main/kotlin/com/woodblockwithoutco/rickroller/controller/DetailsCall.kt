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
        durations.joinToString("\n\n") { duration ->
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