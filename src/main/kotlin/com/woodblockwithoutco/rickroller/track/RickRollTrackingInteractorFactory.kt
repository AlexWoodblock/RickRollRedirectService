// This project is libre, and licenced under the terms of the
// DO WHAT THE FUCK YOU WANT TO PUBLIC LICENCE, version 3.1,
// as published by dtf on July 2019. See the COPYING file or
// https://ph.dtf.wtf/w/wtfpl/#version-3-1 for more details.

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