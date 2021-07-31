package com.flowerhop.nonleakagetask

import androidx.annotation.WorkerThread

abstract class NonLeakageTask(onInterrupted: OnInterruptedListener? = null): BackgroundTask(onInterrupted), NonLeakage {
    @WorkerThread
    override fun run() {
        if (isCancelled()) return
        doInBackground()
        hasDone.set(true)
        clearUIReferences()

        if (isCancelled())
            onCancelled()

        onTaskCompleteListener?.onCompleted()
    }
}