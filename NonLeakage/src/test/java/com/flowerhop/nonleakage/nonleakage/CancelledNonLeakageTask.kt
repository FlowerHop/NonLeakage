package com.flowerhop.nonleakage.nonleakage

import com.flowerhop.nonleakage.NonLeakageTask

class CancelledNonLeakageTask: NonLeakageTask() {
    companion object {
        private const val TAG = "CancelledNonLeakageTask"
    }

    init {
        onCancelledListener = object : OnCancelledListener {
            override fun onCancelled() {
                println("$TAG trigger onCancelled")
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