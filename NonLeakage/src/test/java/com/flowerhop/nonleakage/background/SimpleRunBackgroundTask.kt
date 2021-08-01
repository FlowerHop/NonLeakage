package com.flowerhop.nonleakage.background

import com.flowerhop.nonleakage.BackgroundTask

class SimpleRunBackgroundTask: BackgroundTask() {
    companion object {
        private const val TAG = "SimpleRunBackgroundTask"
    }

    override fun doInBackground() {
        println("$TAG doInBackground...")
    }

    override fun onCancelled() {
    }
}