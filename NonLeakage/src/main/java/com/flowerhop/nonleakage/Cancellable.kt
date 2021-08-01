package com.flowerhop.nonleakage

import java.util.concurrent.atomic.AtomicBoolean

interface Cancellable {
    val isCancelled: AtomicBoolean

    fun onCancelled()

    fun isCancelled() = isCancelled.get()
    fun cancel() {
        isCancelled.compareAndSet(false, true)
    }
}