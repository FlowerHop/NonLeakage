package com.flowerhop.nonleakage

import java.util.concurrent.atomic.AtomicBoolean

abstract class BackgroundTask(onInterrupted: OnInterruptedListener? = null) : Runnable,
    Cancellable {
    interface OnInterruptedListener {
        fun onInterrupted()
    }

    final override var isCancelled: AtomicBoolean private set
    protected val hasDone = AtomicBoolean(false)
    var onInterruptedListener: OnInterruptedListener? = null
    var onTaskCompleteListener: OnTaskCompleteListener? = null

    init {
        isCancelled = AtomicBoolean(false)
        this.onInterruptedListener = onInterrupted
    }

    override fun run() {
        if (isCancelled()) return
        doInBackground()
        hasDone.set(true)

        if (isCancelled())
            onCancelled()

        onTaskCompleteListener?.onCompleted()
    }

    fun hasDone(): Boolean = hasDone.get()

    override fun onCancelled() {
        onInterruptedListener?.onInterrupted()
    }

    protected abstract fun doInBackground()
}