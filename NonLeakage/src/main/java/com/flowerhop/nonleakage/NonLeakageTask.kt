package com.flowerhop.nonleakage


abstract class NonLeakageTask: BackgroundTask(),
    NonLeakage {
    override fun notifyResult() {
        super.notifyResult()
        clearUIReferences()
    }
}