package com.flowerhop.nonleakagetask

import androidx.annotation.WorkerThread

abstract class NonLeakageTask(onCancelled: OnCancelledListener? = null): BackgroundTask(onCancelled) {
    @WorkerThread
    override fun run() {
        doInBackground()
        hasDone.set(true)
        clearUIReferences()

        if (isCancelled())
            return
    }

    abstract fun clearUIReferences()
}

interface Chainable {
    fun onNext()
}