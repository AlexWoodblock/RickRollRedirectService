// This project is libre, and licenced under the terms of the
// DO WHAT THE FUCK YOU WANT TO PUBLIC LICENCE, version 3.1,
// as published by dtf on July 2019. See the COPYING file or
// https://ph.dtf.wtf/w/wtfpl/#version-3-1 for more details.

package com.woodblockwithoutco.rickroller

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.woodblockwithoutco.rickroller.db.createDatabase
import com.woodblockwithoutco.rickroller.routing.setup
import com.woodblockwithoutco.rickroller.track.createAndPrepareRickRollTrackingInteractor
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import org.slf4j.LoggerFactory
import java.util.Locale

/**
 * Main entry point for our Rick Roll Redirect Service.
 */
fun main() {
    updateLogLevel()

    val location: String? = System.getenv(LOCALE_COUNTRY)
    val lang: String? = System.getenv(LOCALE_LANG)

    val locale = if (location != null && lang != null) {
        Locale(lang, location)
    } else {
        null
    }

    val port = System.getenv(PORT_NAME)?.toIntOrNull() ?: DEFAULT_PORT

    val dbUrl = System.getenv(DATABASE_URL_NAME) ?: throw NullPointerException(
        "$DATABASE_URL_NAME is missing from system environment; aborting..."
    )

    createDatabase(dbUrl)
    val rickRollTrackingInteractor = createAndPrepareRickRollTrackingInteractor()
    embeddedServer(Netty, port) {
        routing {
            setup(
                rickRollTrackingInteractor = rickRollTrackingInteractor,
                locale = locale
            )
        }
    }.start(wait = true)
}

private fun updateLogLevel() {
    val root: Logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
    root.level = if (isDebug) {
        Level.ALL
    } else {
        Level.INFO
    }
}

private val isDebug: Boolean
    get() = System.getenv("DEBUG") != null

private const val DATABASE_URL_NAME = "JDBC_DATABASE_URL"
private const val DEFAULT_PORT = 8080
private const val PORT_NAME = "PORT"
private const val LOCALE_COUNTRY = "LOCALE_COUNTRY"
private const val LOCALE_LANG = "LOCALE_LANG"