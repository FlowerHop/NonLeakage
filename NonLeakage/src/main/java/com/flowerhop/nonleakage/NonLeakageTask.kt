package com.flowerhop.nonleakage


abstract class NonLeakageTask(onCancelled: OnCancelledListener? = null): BackgroundTask(onCancelled),
    NonLeakage {
    override fun notifyResult() {
        super.notifyResult()
        clearUIReferences()
    }
}