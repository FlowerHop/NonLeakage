package com.flowerhop.nonleakage

import com.flowerhop.nonleakage.background.ExceptionBackgroundTask
import com.flowerhop.nonleakage.background.FakeBackgroundTask
import com.flowerhop.nonleakage.background.SimpleRunBackgroundTask
import org.junit.Assert.*
import org.junit.Test

class ChainTasksExecutorTest {
    @Test
    fun chain3TasksSuccessfully() {
        // Arrange
        val executor = ChainTasksExecutor()
        val expectedCompletedCount = 4
        val tasks = listOf<BackgroundTask>(
            SimpleRunBackgroundTask("A"),
            SimpleRunBackgroundTask("B"),
            SimpleRunBackgroundTask("C"),
            SimpleRunBackgroundTask("D"),
        )

        // Act
        var completedCount = 0
        tasks.forEach { executor.chain(it) }
        Thread.sleep(500)
        tasks.forEach { if (it.hasDone()) completedCount++ }

        // Assert
        assertEquals(expectedCompletedCount, completedCount)
    }

    @Test
    fun `Chain can work even there is a cancelled task`() {
        // Arrange
        val executor = ChainTasksExecutor()
        val expectedCompletedCount = 6
        val tasks = listOf<BackgroundTask>(
            FakeBackgroundTask(100,"A"),
            FakeBackgroundTask(100, "B"),
            FakeBackgroundTask(100, "C"),
            FakeBackgroundTask(100, "D"),
            FakeBackgroundTask(100, "E"),
            FakeBackgroundTask(100, "F"),
        )

        // Act
        var completedCount = 0
        tasks.forEach { executor.chain(it) }

        tasks[2].cancel()
        tasks[4].cancel()
        tasks[5].cancel()
        Thread.sleep(800)
        tasks.forEach { if (it.hasDone()) completedCount++ }

        // Assert
        assertEquals(expectedCompletedCount, completedCount)
    }

    @Test
    fun `Chain can work when exception happened`() {
        // Arrange
        val executor = ChainTasksExecutor()
        val expectedCompletedCount = 6
        val tasks = listOf<BackgroundTask>(
            FakeBackgroundTask(100,"A"),
            FakeBackgroundTask(100, "B"),
            ExceptionBackgroundTask(100, "C"),
            FakeBackgroundTask(100, "D"),
            FakeBackgroundTask(100, "E"),
            FakeBackgroundTask(100, "F"),
        )

        // Act
        var completedCount = 0
        tasks.forEach { executor.chain(it) }
        Thread.sleep(800)
        tasks.forEach { if (it.hasDone()) completedCount++ }

        // Assert
        assertEquals(expectedCompletedCount, completedCount)
    }
}