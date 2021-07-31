package com.flowerhop.nonleakagetask.background

import com.flowerhop.nonleakagetask.BackgroundTask

class CancelledBackgroundTask: BackgroundTask(
    object : OnCancelledListener {
        override fun onCancelled() {
            println("$TAG trigger onCancelled...")
        }
    }) {
    companion object {
        private const val TAG = "CancelledBackgroundTask"
    }

    override fun doInBackground() {
        println("$TAG doInBackground...")
        println("$TAG Cancel!")
        cancel();
    }
}