package com.flowerhop.nonleakage.background

import com.flowerhop.nonleakage.BackgroundTask

class ExceptionBackgroundTask(private val timeToThrow: Long, private val name: String = TAG): BackgroundTask() {
    companion object {
        private const val TAG = "ExceptionBackgroundTask"
    }


    override fun doInBackground() {
        Thread.sleep(timeToThrow)
        throw Exception("")
    }

    override fun onCancelled() {
        printMsg("trigger onCancelled")
    }

    private fun printMsg(msg: String) {
        println("$TAG $name $msg")
    }
}
