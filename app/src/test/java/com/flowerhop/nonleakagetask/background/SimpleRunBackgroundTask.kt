package com.flowerhop.nonleakagetask.background

import com.flowerhop.nonleakagetask.BackgroundTask

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