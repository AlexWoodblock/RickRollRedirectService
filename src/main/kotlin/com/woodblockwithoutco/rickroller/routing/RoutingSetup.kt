// This project is libre, and licenced under the terms of the
// DO WHAT THE FUCK YOU WANT TO PUBLIC LICENCE, version 3.1,
// as published by dtf on July 2019. See the COPYING file or
// https://ph.dtf.wtf/w/wtfpl/#version-3-1 for more details.

package com.woodblockwithoutco.rickroller.routing

import com.woodblockwithoutco.rickroller.controller.handleDefaultGetCall
import com.woodblockwithoutco.rickroller.controller.handleDetailsCall
import com.woodblockwithoutco.rickroller.track.RickRollTrackingInteractor
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import java.util.Locale

/**
 * Setup the application routing.
 */
fun Routing.setup(
    rickRollTrackingInteractor: RickRollTrackingInteractor,
    locale: Locale? = null
) {
    get("/") {
        handleDefaultGetCall(rickRollTrackingInteractor)
    }

    get("/details") {
        handleDetailsCall(rickRollTrackingInteractor)
    }
}