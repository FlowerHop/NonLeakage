package com.flowerhop.nonleakage.nonleakage

import org.junit.Assert
import org.junit.Test
import java.util.concurrent.Executors

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
    fun taskCanBeInterrupted() {
        // Arrange
        val task = InterruptedNonLeakageTask()

        // Act
        task.run()

        // Assert
        Assert.assertTrue(task.isCancelled())
    }

    @Test
    fun tasksCanBeChained() {
        val executors = Executors.newSingleThreadExecutor()
        val taskA = FakeNonLeakageTask(50, "Task A")
        val taskB = FakeNonLeakageTask(50, "Task B")
        val taskC = FakeNonLeakageTask(50, "Task C")

        taskA.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                executors.execute(taskB)
            }
        }
        taskB.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                executors.execute(taskC)
            }
        }

        var done = false
        taskC.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                done = true
            }
        }

        executors.execute(taskA)
        Thread.sleep(300)
        Assert.assertTrue(done)
    }

    @Test
    fun `The chained task can be cancelled when its parent is cancelled`() {
        val executors = Executors.newSingleThreadExecutor()
        val taskA = FakeNonLeakageTask(100, "Task A")
        val taskB = FakeNonLeakageTask(100, "Task B")
        val taskC = FakeNonLeakageTask(100, "Task C")

        taskA.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                executors.execute(taskB)
            }
        }
        taskB.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {

                executors.execute(taskC)
            }
        }

        var done = false
        taskC.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                done = true
            }
        }

        executors.execute(taskA)
        taskB.cancel()
        Thread.sleep(500)
        Assert.assertFalse(done)
    }

    @Test
    fun `When the done task is cancelled, the chain can keep running`() {
        val executors = Executors.newSingleThreadExecutor()
        val taskA = FakeNonLeakageTask(10, "Task A")
        val taskB = FakeNonLeakageTask(100, "Task B")
        val taskC = FakeNonLeakageTask(100, "Task C")

        taskA.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                executors.execute(taskB)
            }
        }
        taskB.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                executors.execute(taskC)
            }
        }

        var done = false
        taskC.onTaskCompleteListener = object : com.flowerhop.nonleakage.OnTaskCompleteListener {
            override fun onCompleted() {
                done = true
            }
        }

        executors.execute(taskA)
        Thread.sleep(110)
        taskA.cancel()
        Thread.sleep(500)
        Assert.assertTrue(done)
    }
}