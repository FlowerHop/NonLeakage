package com.flowerhop.nonleakagetask

class CancelledNonLeakageTask: NonLeakageTask() {
    override fun doJob() {
        println("doJob...")
        println("Cancel!")
        cancel()
    }

    override fun onCancelled() {
        println("trigger onCancelled")
    }

    override fun onNext() {
    }
}