package com.flowerhop.nonleakage.nonleakage

class InterruptedNonLeakageTask: com.flowerhop.nonleakage.NonLeakageTask() {
    companion object {
        private const val TAG = "InterruptedNonLeakageTask"
    }

    init {
        onInterruptedListener = object : OnInterruptedListener {
            override fun onInterrupted() {
                println("$TAG trigger onInterrupted")
            }
        }
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