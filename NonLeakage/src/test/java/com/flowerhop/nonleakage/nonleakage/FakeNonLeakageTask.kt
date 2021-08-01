package com.flowerhop.nonleakage.nonleakage

import com.flowerhop.nonleakage.NonLeakageTask

class FakeNonLeakageTask(private val time: Long, private val taskName: String): NonLeakageTask() {
    companion object {
        private const val TAG = "FakeNonLeakageTask"
        private const val SLEEP_PERIOD = 50L
    }

    init {
        onCancelledListener = object : OnCancelledListener {
            override fun onCancelled() {
                println("$TAG $taskName is triggered onInterrupted")
            }

        }
    }

    private var passedTime = 0L

    override fun doInBackground() {
        println("$TAG: $taskName starts doInBackground for $time")

        while(passedTime < time) {
            if (isCancelled()) break
            println("$TAG: $taskName do at $passedTime")
            Thread.sleep(SLEEP_PERIOD)
            passedTime += SLEEP_PERIOD
        }

        println("$TAG: $taskName end doInBackground at $passedTime")
    }

    override fun clearUIReferences() {
        println("$TAG: $taskName clearUIReferences")
    }
}