package com.flowerhop.nonleakage.background

class SimpleRunBackgroundTask: com.flowerhop.nonleakage.BackgroundTask() {
    companion object {
        private const val TAG = "SimpleRunBackgroundTask"
    }

    override fun doInBackground() {
        println("$TAG doInBackground...")
    }

    override fun onCancelled() {
    }
}