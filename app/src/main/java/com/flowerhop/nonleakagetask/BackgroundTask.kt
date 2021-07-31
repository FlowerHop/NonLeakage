package com.flowerhop.nonleakagetask

import androidx.annotation.WorkerThread
import java.util.concurrent.atomic.AtomicBoolean

abstract class BackgroundTask : Runnable, Cancellable {
    final override var isCancelled: AtomicBoolean private set
    private val hasDone = AtomicBoolean(false)

    @WorkerThread
    override fun run() {
        doInBackground();

        if (isCancelled())
            onCancelled()

        hasDone.set(true)
    }

    protected abstract fun doInBackground();
    fun hasDone(): Boolean = hasDone.get()

    init {
        isCancelled = AtomicBoolean(false)
    }
}