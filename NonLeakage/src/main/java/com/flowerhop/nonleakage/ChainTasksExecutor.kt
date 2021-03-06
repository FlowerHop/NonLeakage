package com.flowerhop.nonleakage

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicBoolean

open class ChainTasksExecutor {
    private var isShutdown: AtomicBoolean = AtomicBoolean(false)
    private var threadPool: ExecutorService = Executors.newFixedThreadPool(1)
    private var taskQueue: LinkedBlockingDeque<BackgroundTask> = LinkedBlockingDeque<BackgroundTask>()
    private var status: Status = Status.READY
    private var executingTask: BackgroundTask? = null

    @Synchronized fun chain(backgroundTask: BackgroundTask) {
        if (isShutdown.get()) return
        onOfferingTask(backgroundTask)

        if (status == Status.BUSY) return

        status = Status.BUSY
        runNext()
    }

    @Synchronized private fun runNext() {
        if (taskQueue.size <= 0) {
            status = Status.READY
            return
        }

        executingTask = onPollingTask()
            ?.also {
                it.onTaskCompleteListener = object : OnTaskCompleteListener {
                    override fun onCompleted() {
                        runNext()
                    }
                }
            }

        threadPool.execute(executingTask)
    }

    protected open fun onOfferingTask(backgroundTask: BackgroundTask) {
        taskQueue.offer(backgroundTask)
    }

    protected open fun onPollingTask(): BackgroundTask? {
        return taskQueue.poll()
    }

    open fun shutdown() {
        if (!isShutdown.compareAndSet(false, true)) return
        threadPool.shutdown()
        clearTaskQueue()
    }

    open fun shutdownNow() {
        if (!isShutdown.compareAndSet(false, true)) return
        threadPool.shutdownNow()
        clearTaskQueue()
    }

    protected open fun clearTaskQueue() {
        executingTask?.onTaskCompleteListener = null
        executingTask = null
        taskQueue = LinkedBlockingDeque<BackgroundTask>()
    }
}

enum class Status {
    READY, BUSY
}