package com.flowerhop.nonleakagetask

abstract class NonLeakageTask: BackgroundTask(), Chainable {
    override fun doInBackground() {
        doJob()
        onNext()
    }

    abstract fun doJob()
}

interface Chainable {
    fun onNext()
}