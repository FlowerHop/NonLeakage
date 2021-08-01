package com.flowerhop.nonleakage


abstract class NonLeakageTask(onInterrupted: OnInterruptedListener? = null): BackgroundTask(onInterrupted),
    NonLeakage {
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