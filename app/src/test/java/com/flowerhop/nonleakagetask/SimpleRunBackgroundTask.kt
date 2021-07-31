package com.flowerhop.nonleakagetask

class SimpleRunBackgroundTask: BackgroundTask() {
    override fun doInBackground() {
        println("doInBackground...")
    }

    override fun onCancelled() {
    }
}