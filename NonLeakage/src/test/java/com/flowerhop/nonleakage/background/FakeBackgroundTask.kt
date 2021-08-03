package com.flowerhop.nonleakage.background

import com.flowerhop.nonleakage.BackgroundTask


class FakeBackgroundTask(private val time: Long, private val name: String = TAG): BackgroundTask() {
    companion object {
        private const val TAG = "FakeBackgroundTask"
        private const val SLEEP_PERIOD = 50L
    }

    private var passedTime = 0L

    override fun doInBackground() {
        printMsg("start doInBackground for $time")

        while(passedTime < time) {
            if (isCancelled()) break
            printMsg("do at $passedTime")
            Thread.sleep(SLEEP_PERIOD)
            passedTime += SLEEP_PERIOD
        }

        printMsg("end doInBackground at $passedTime")
    }

    override fun onCancelled() {
        printMsg("fakeBackgroundTask is cancelled")
    }

    private fun printMsg(msg: String) {
        println("$TAG $name $msg")
    }
}