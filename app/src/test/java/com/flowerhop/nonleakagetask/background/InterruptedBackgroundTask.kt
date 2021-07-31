package com.flowerhop.nonleakagetask.background

import com.flowerhop.nonleakagetask.BackgroundTask

class InterruptedBackgroundTask: BackgroundTask() {
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
        cancel();
    }
}