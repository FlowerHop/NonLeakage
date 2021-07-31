package com.flowerhop.nonleakagetask

class SimpleRunNonLeakageTask: NonLeakageTask() {
    override fun doJob() {
        println("doJob")
    }

    override fun onCancelled() {
    }

    override fun onNext() {
    }
}