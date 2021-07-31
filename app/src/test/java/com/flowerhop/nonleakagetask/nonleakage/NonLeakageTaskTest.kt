package com.flowerhop.nonleakagetask.nonleakage

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

    @Test
    fun tasksCanBeChained() {
        TODO()
    }

    @Test
    fun `The chained task can be cancelled`() {
        TODO("Not yet implemented")
    }

    @Test
    fun `Cancel the running task can also stop the chain`() {
        TODO("Not yet implemented")
    }

    @Test
    fun `When the done task is cancelled, the chain can keep running`() {
        TODO("Not yet implemented")
    }
}