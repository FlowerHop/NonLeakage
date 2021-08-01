package com.flowerhop.nonleakage

import java.util.concurrent.atomic.AtomicBoolean

interface Cancellable {
    val cancelled: AtomicBoolean

    fun onCancelled()

    fun isCancelled() = cancelled.get()
    fun cancel() {
        cancelled.compareAndSet(false, true)
    }
}