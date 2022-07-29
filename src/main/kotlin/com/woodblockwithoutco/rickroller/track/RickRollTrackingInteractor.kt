package com.woodblockwithoutco.rickroller.track

import com.woodblockwithoutco.rickroller.db.RickRolledTable
import com.woodblockwithoutco.rickroller.util.rethrowIfCancellation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Duration
import java.time.LocalDateTime
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Tracks and retrieves number of people that were Rick Rolled.
 */
class RickRollTrackingInteractor(
    private val ioDispatcher: CoroutineDispatcher,
) {

    private val logger by lazy {
        Logger.getLogger(LOGGER_NAME)
    }

    private val trackWriteScope = CoroutineScope(ioDispatcher)

    /**
     * Prepare required resources.
     */
    fun prepareSync() {
        transaction {
            SchemaUtils.create(RickRolledTable)
        }
    }

    /**
     * Should be called when we got another poor soul.
     */
    fun gotAnotherOne() {
        trackWriteScope.launch {
            try {
                gotAnotherOneSuspending()
            } catch (e: Exception) {
                e.rethrowIfCancellation()
                logger.log(Level.WARNING, "Failed to record Rick Roll", e)
            }
        }
    }

    private suspend fun gotAnotherOneSuspending() {
        newSuspendedTransaction(ioDispatcher) {
            val latestId = RickRolledTable.insert {

            } get RickRolledTable.id

            val oldestId = latestId.value - MAX_LOG_SIZE

            val removed = RickRolledTable.deleteWhere {
                RickRolledTable.id lessEq oldestId
            }

            logger.log(Level.FINE, "Removed $removed during log culling")
        }
    }

    /**
     * Get list of how long ago people were Rick Rolled.
     */
    suspend fun getTimesAgoRickRolled(): List<Duration> {
        return newSuspendedTransaction(ioDispatcher) {
            RickRolledTable.selectAll().map { resultRow ->
                val time = resultRow[RickRolledTable.at]

                Duration.between(time, LocalDateTime.now())
            }
        }
    }

    private companion object {
        private const val LOGGER_NAME = "RickRollTracker"

        private const val MAX_LOG_SIZE = 100
    }
}