package com.flowerhop.nonleakage


abstract class NonLeakageTask(onCancelled: OnCancelledListener? = null): BackgroundTask(onCancelled),
    NonLeakage {
    override fun run() {
        doInBackground()
        hasDone.set(true)
        clearUIReferences()

        if (isCancelled())
            onCancelled()

        onTaskCompleteListener?.onCompleted()
    }
}