package com.flowerhop.nonleakagetask

import androidx.annotation.WorkerThread
import java.util.concurrent.atomic.AtomicBoolean

interface Cancellable {
    val isCancelled: AtomicBoolean

    @WorkerThread
    fun onCancelled()

    fun isCancelled() = isCancelled.get()
    fun cancel() {
        isCancelled.compareAndSet(false, true)
    }
}