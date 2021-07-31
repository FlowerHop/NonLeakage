package com.flowerhop.nonleakagetask.nonleakage

import com.flowerhop.nonleakagetask.NonLeakageTask

class SimpleRunNonLeakageTask: NonLeakageTask() {
    companion object {
        private const val TAG = "SimpleRunNonLeakageTask"
    }
    override fun doInBackground() {
        println("$TAG doInBackground")
    }

    override fun clearUIReferences() {
        println("$TAG clearUIReferences")
    }
}