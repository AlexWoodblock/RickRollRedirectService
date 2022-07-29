package com.woodblockwithoutco.rickroller.data

import java.time.Duration
import java.time.LocalDateTime

/**
 * Data for a Rick Rolled person.
 */
data class RickRolledData(
    val time: LocalDateTime,
    val durationAgo: Duration
)