package com.flowerhop.nonleakagetask

import java.util.concurrent.LinkedBlockingDeque

class ChainNonLeakageTasksExecutor: ChainTasksExecutor(), NonLeakage {
    private var nonLeakages = LinkedBlockingDeque<NonLeakage>()
    private var executingNonLeakage: NonLeakage? = null

    override fun onOfferingTask(backgroundTask: BackgroundTask) {
        nonLeakages.offer(backgroundTask as NonLeakage)
        super.onOfferingTask(backgroundTask)
    }

    override fun onPollingTask(): BackgroundTask? {
        executingNonLeakage = nonLeakages.poll()
        return super.onPollingTask()
    }
    
    override fun clearUIReferences() {
        executingNonLeakage?.clearUIReferences()
        nonLeakages.forEach { it.clearUIReferences() }
    }

    override fun clearTaskQueue() {
        clearUIReferences()
        super.clearTaskQueue()
    }
}