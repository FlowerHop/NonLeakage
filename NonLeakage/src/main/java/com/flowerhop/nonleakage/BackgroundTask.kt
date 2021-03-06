package com.flowerhop.nonleakage

import java.util.concurrent.atomic.AtomicBoolean

abstract class BackgroundTask : Runnable,
    Cancellable {
    final override var cancelled: AtomicBoolean private set
    private val hasDone = AtomicBoolean(false)
    var onTaskCompleteListener: OnTaskCompleteListener? = null

    init {
        cancelled = AtomicBoolean(false)
    }

    override fun run() {
        doInBackground()
        hasDone.set(true)
        notifyResult()
        onTaskCompleteListener?.onCompleted()
    }

    protected open fun notifyResult() {
        if (isCancelled())
            onCancelled()
        else
            onCompleted()
    }

    fun hasDone(): Boolean = hasDone.get()

    override fun onCancelled() {}

    protected open fun onCompleted() {}

    protected abstract fun doInBackground()
}
