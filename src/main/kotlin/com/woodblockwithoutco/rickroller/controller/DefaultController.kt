package com.woodblockwithoutco.rickroller.controller

import com.woodblockwithoutco.rickroller.data.YOU_KNOW_THE_RULES
import com.woodblockwithoutco.rickroller.track.RickRollTrackingInteractor
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respondRedirect
import io.ktor.util.pipeline.PipelineContext

/**
 * Handles GET call to / path.
 */
suspend fun PipelineContext<Unit, ApplicationCall>.handleDefaultGetCall(
    rickRollTrackingInteractor: RickRollTrackingInteractor
) {
    rickRollTrackingInteractor.gotAnotherOne()

    // TFW no AsyncTask and you have to use Coroutines :(
    call.respondRedirect(
        url = YOU_KNOW_THE_RULES,
        permanent = false
    )

}