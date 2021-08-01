package com.flowerhop.nonleakage.nonleakage

import com.flowerhop.nonleakage.NonLeakageTask

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