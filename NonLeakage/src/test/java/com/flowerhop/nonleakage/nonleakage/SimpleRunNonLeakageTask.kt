package com.flowerhop.nonleakage.nonleakage

class SimpleRunNonLeakageTask: com.flowerhop.nonleakage.NonLeakageTask() {
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