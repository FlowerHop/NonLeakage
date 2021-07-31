package com.flowerhop.nonleakagetask

import org.junit.Assert
import org.junit.Test

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
}