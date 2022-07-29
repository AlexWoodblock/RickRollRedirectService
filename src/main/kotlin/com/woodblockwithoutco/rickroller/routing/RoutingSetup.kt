package com.woodblockwithoutco.rickroller.routing

import com.woodblockwithoutco.rickroller.controller.handleDefaultGetCall
import com.woodblockwithoutco.rickroller.controller.handleDetailsCall
import com.woodblockwithoutco.rickroller.track.RickRollTrackingInteractor
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

/**
 * Setup the application routing.
 */
fun Routing.setup(
    rickRollTrackingInteractor: RickRollTrackingInteractor
) {
    get("/") {
        handleDefaultGetCall(rickRollTrackingInteractor)
    }

    get("/details") {
        handleDetailsCall(rickRollTrackingInteractor)
    }
}