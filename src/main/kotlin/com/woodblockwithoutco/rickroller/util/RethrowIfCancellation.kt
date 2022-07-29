package com.woodblockwithoutco.rickroller.util

import kotlinx.coroutines.CancellationException

/**
 * Rethrow exception if this [Throwable] is instance of [CancellationException].
 */
fun Throwable.rethrowIfCancellation() {
    if (this is CancellationException) {
        throw this
    }
}