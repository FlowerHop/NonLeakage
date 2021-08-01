package com.flowerhop.nonleakage.background

import com.flowerhop.nonleakage.BackgroundTask


class FakeBackgroundTask(private val time: Long): BackgroundTask() {
    companion object {
        private const val TAG = "FakeBackgroundTask"
        private const val SLEEP_PERIOD = 50L
    }

    private var passedTime = 0L

    override fun doInBackground() {
        println("$TAG start doInBackground for $time")

        while(passedTime < time) {
            if (isCancelled()) break
            println("$TAG do at $passedTime")
            Thread.sleep(SLEEP_PERIOD)
            passedTime += SLEEP_PERIOD
        }

        println("$TAG end doInBackground at $passedTime")
    }
}