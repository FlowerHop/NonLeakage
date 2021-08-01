package com.flowerhop.nonleakage.background

import com.flowerhop.nonleakage.BackgroundTask
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.Executors

class BackgroundTaskTest {
    @Test
    fun taskCanRun() {
        // Arrange
        val task = SimpleRunBackgroundTask()

        // Act
        task.run()

        // Assert
        Assert.assertTrue(task.hasDone())
    }
    
    @Test
    fun taskCanBeCancelled() {
        // Arrange
        val cancelledBackgroundTask = CancelledBackgroundTask()

        // Act
        cancelledBackgroundTask.run()

        // Assert
        Assert.assertTrue(cancelledBackgroundTask.isCancelled())
    }

    @Test
    fun `Task is not cancelled for no reason`() {
        // Arrange
        val task = SimpleRunBackgroundTask()

        // Act
        task.run()

        // Assert
        Assert.assertFalse(task.isCancelled())
    }

    @Test
    fun `Task can be interrupted when running`() {
        // Arrange
        val fakeBackgroundTask = FakeBackgroundTask(2000)
        fakeBackgroundTask.onCancelledListener = object : BackgroundTask.OnCancelledListener {
            override fun onCancelled() {
                println("fakeBackgroundTask is cancelled")
            }
        }

        val executor = Executors.newSingleThreadExecutor()
        executor.execute(fakeBackgroundTask)

        // Act
        Thread.sleep(100)
        fakeBackgroundTask.cancel()
        // Sleep for watch log
        Thread.sleep(1000)

        // Assert
        Assert.assertTrue(fakeBackgroundTask.isCancelled())
    }

    @Test
    fun `Cancelled a running task, and clean onInterruptedListener successfully`() {
        // Arrange
        var onCancelledCalled = false
        val fakeBackgroundTask = FakeBackgroundTask(2000)
        fakeBackgroundTask.onCancelledListener = object : BackgroundTask.OnCancelledListener {
            override fun onCancelled() {
                println("fakeBackgroundTask is cancelled")
                onCancelledCalled = true
            }
        }

        val executor = Executors.newSingleThreadExecutor()
        executor.execute(fakeBackgroundTask)

        // Act
        Thread.sleep(100)
        fakeBackgroundTask.onCancelledListener = null
        fakeBackgroundTask.cancel()
        // Sleep for watch log
        Thread.sleep(1000)

        // Assert
        Assert.assertFalse(onCancelledCalled)
    }
}