package com.flowerhop.nonleakage.background

class InterruptedBackgroundTask: com.flowerhop.nonleakage.BackgroundTask() {
    companion object {
        private const val TAG = "InterruptedBackgroundTask"
    }

    init {
        onInterruptedListener = object : OnInterruptedListener {
            override fun onInterrupted() {
                println("$TAG trigger onInterrupted...")
            }
        }
    }

    override fun doInBackground() {
        println("$TAG doInBackground...")
        println("$TAG Cancel!")
        cancel()
    }
}