package com.flowerhop.nonleakagetask

import org.junit.Assert
import org.junit.Test

class NonLeakageTaskTest {
    @Test
    fun taskCanRun() {
        // Arrange
        val task = SimpleRunNonLeakageTask()

        // Act
        task.run()

        // Assert
        Assert.assertTrue(task.hasDone())
    }

    @Test
    fun taskCanBeCancelled() {
        // Arrange
        val task = CancelledNonLeakageTask()

        // Act
        task.run()

        // Assert
        Assert.assertTrue(task.isCancelled())
    }
}