package com.flowerhop.nonleakage.background

import com.flowerhop.nonleakage.BackgroundTask

class CancelledBackgroundTask: BackgroundTask() {
    companion object {
        private const val TAG = "CancelledBackgroundTask"
    }

    init {
        onCancelledListener = object : OnCancelledListener {
            override fun onCancelled() {
                println("$TAG trigger onCancelled...")
            }
        }
    }

    override fun doInBackground() {
        println("$TAG doInBackground...")
        println("$TAG Cancel!")
        cancel()
    }
}