package com.flowerhop.nonleakage.background

import com.flowerhop.nonleakage.BackgroundTask

class SimpleRunBackgroundTask(private val name: String = TAG): BackgroundTask() {
    companion object {
        private const val TAG = "SimpleRunBackgroundTask"
    }

    override fun doInBackground() {
        printMsg("doInBackground...")
    }

    override fun onCancelled() {
    }

    private fun printMsg(msg: String) {
        println("$TAG [$name] $msg")
    }
}