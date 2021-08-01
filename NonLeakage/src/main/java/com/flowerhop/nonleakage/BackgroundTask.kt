package com.flowerhop.nonleakage

import java.util.concurrent.atomic.AtomicBoolean

abstract class BackgroundTask(onCancelled: OnCancelledListener? = null) : Runnable,
    Cancellable {
    interface OnCancelledListener {
        fun onCancelled()
    }

    final override var isCancelled: AtomicBoolean private set
    protected val hasDone = AtomicBoolean(false)
    var onCancelledListener: OnCancelledListener? = null
    var onTaskCompleteListener: OnTaskCompleteListener? = null

    init {
        isCancelled = AtomicBoolean(false)
        this.onCancelledListener = onCancelled
    }

    override fun run() {
        doInBackground()
        hasDone.set(true)

        if (isCancelled())
            onCancelled()

        onTaskCompleteListener?.onCompleted()
    }

    fun hasDone(): Boolean = hasDone.get()

    override fun onCancelled() {
        onCancelledListener?.onCancelled()
    }

    protected abstract fun doInBackground()
}
