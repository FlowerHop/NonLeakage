package com.flowerhop.nonleakagetask

import androidx.annotation.WorkerThread
import java.util.concurrent.atomic.AtomicBoolean

abstract class BackgroundTask(onCancelled: OnCancelledListener? = null) : Runnable, Cancellable {
    interface OnCancelledListener {
        @WorkerThread
        fun onCancelled()
    }

    final override var isCancelled: AtomicBoolean private set
    protected val hasDone = AtomicBoolean(false)
    var onCancelledListener: OnCancelledListener? = null

    init {
        isCancelled = AtomicBoolean(false)
        this.onCancelledListener = onCancelled
    }

    @WorkerThread
    override fun run() {
        doInBackground();
        hasDone.set(true)

        if (isCancelled())
            onCancelled()
    }

    fun hasDone(): Boolean = hasDone.get()
    override fun onCancelled() {
        onCancelledListener?.onCancelled()
    }

    protected abstract fun doInBackground()
}
