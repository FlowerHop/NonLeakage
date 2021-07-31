package com.flowerhop.nonleakagetask

import androidx.annotation.WorkerThread
import java.util.concurrent.atomic.AtomicBoolean

abstract class BackgroundTask(onCancelled: OnCancelledListener? = null) : Runnable, Cancellable {
    interface OnCancelledListener {
        fun onCancelled()
    }

    final override var isCancelled: AtomicBoolean private set
    private val hasDone = AtomicBoolean(false)
    var onCancelledListener: OnCancelledListener? = null

    init {
        this.onCancelledListener = onCancelled
    }

    init {
        isCancelled = AtomicBoolean(false)
    }

    @WorkerThread
    override fun run() {
        doInBackground();

        if (isCancelled())
            onCancelled()

        hasDone.set(true)
    }

    fun hasDone(): Boolean = hasDone.get()
    override fun onCancelled() {
        onCancelledListener?.onCancelled()
    }

    protected abstract fun doInBackground()
}
