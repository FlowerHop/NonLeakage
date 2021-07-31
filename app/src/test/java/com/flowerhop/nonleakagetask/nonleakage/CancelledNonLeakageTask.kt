package com.flowerhop.nonleakagetask.nonleakage

import com.flowerhop.nonleakagetask.NonLeakageTask

class CancelledNonLeakageTask: NonLeakageTask() {
    companion object {
        private const val TAG = "CancelledNonLeakageTask"
    }

    override fun onCancelled() {
        println("$TAG trigger onCancelled")
    }

    override fun doInBackground() {
        println("$TAG doInBackground...")
        println("$TAG Cancel!")
        cancel()
    }

    override fun clearUIReferences() {
        println("$TAG clearUIReferences")
    }
}