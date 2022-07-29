package com.woodblockwithoutco.rickroller.track

import kotlinx.coroutines.Dispatchers

/**
 * Create instance of [RickRollTrackingInteractor].
 *
 * This call may fail.
 */
fun createAndPrepareRickRollTrackingInteractor(): RickRollTrackingInteractor {
    val rickRollTrackingInteractor = RickRollTrackingInteractor(
        Dispatchers.IO
    )
    rickRollTrackingInteractor.prepareSync()

    return rickRollTrackingInteractor
}