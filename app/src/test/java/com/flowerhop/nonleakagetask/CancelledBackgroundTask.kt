package com.flowerhop.nonleakagetask

class CancelledBackgroundTask: BackgroundTask() {
    override fun doInBackground() {
        println("doInBackground...")
        println("Cancel!")
        cancel();
    }

    override fun onCancelled() {
        println("trigger onCancelled...")
    }
}