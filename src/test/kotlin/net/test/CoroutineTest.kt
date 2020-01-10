package net.test

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class CoroutineTest {

    @Test
    fun coroutine() {
        GlobalScope.launch {
            // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello,") // main thread continues while coroutine is delayed
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    }

    @Test
    fun coroutine1() {
        GlobalScope.launch {
            // launch a new coroutine in background and continue
            delay(1000L)
            println("World!")
        }
        println("Hello,") // main thread continues here immediately
        runBlocking {
            // but this expression blocks the main thread
            delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
        }
    }

    @Test
    fun coroutine2() = runBlocking {
        // start main coroutine
        GlobalScope.launch {
            // launch a new coroutine in background and continue
            delay(1000L)
            println("World!")
        }
        println("Hello,") // main coroutine continues here immediately
        delay(2000L)      // delaying for 2 seconds to keep JVM alive
    }

    @Test
    fun coroutine3() = runBlocking {
        // start main coroutine
        val job = GlobalScope.launch {
            // launch a new coroutine and keep a reference to its Job
            delay(100L)
            println("World!")
        }
        println("Hello,")
        job.join() // wait until child coroutine completes
    }

    /**
     * Every coroutine builder, including runBlocking, adds an instance of CoroutineScope to the scope of its code block.
     * We can launch coroutines in this scope without having to join them explicitly,
     * because an outer coroutine (runBlocking in our example) does not complete until all the coroutines launched in its scope complete.
     */
    @Test
    fun coroutine4() = runBlocking {
        // this: CoroutineScope
        launch {
            // launch a new coroutine in the scope of runBlocking
            delay(1000L)
            println("World!")
        }
        println("Hello,")
    }

    /**
     * with extracted suspend function
     */
    @Test
    fun coroutine41() = runBlocking {
        // this: CoroutineScope
        launch {
            // launch a new coroutine in the scope of runBlocking
            doWorld()
        }
        println("Hello,")
    }

    private suspend fun doWorld() {
        delay(1000L)
        println("World!")
    }

    /**
     * In addition to the coroutine scope provided by different builders, it is possible to declare your own scope using coroutineScope builder.
     * It creates a coroutine scope and does not complete until all launched children complete.
     * The main difference between runBlocking and coroutineScope is that the latter does not block the current thread while waiting for all children to complete.
     */
    @Test
    fun coroutine5() = runBlocking {
        // this: CoroutineScope
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope {
            // Creates a coroutine scope
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // This line will be printed before the nested launch
        }

        println("Coroutine scope is over") // This line is not printed until the nested launch completes
    }

    @Test
    fun manyCoroutines() = runBlocking {
        repeat(100_000) {
            // launch a lot of coroutines
            launch {
                delay(1000L)
                print(".")
            }
        }
    }

    /**
     * Active coroutines that were launched in GlobalScope do not keep the process alive. They are like daemon threads.
     */
    @Test
    fun daemon() = runBlocking {
        GlobalScope.launch {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // just quit after delay
    }
}