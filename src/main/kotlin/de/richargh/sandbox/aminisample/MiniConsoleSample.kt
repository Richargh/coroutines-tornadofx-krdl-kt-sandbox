package de.richargh.sandbox.aminisample

import de.richargh.sandbox.cornado.info
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

class MiniConsoleSample {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            info("Before CoroutineScope")
            // enter coroutine land
            enter()
            info("After CoroutineScope")
        }

        // to enter coroutine land we need a the runBlocking builder that creates a coroutine
        // this coroutine blocks the thread until it completes
        // inside the coroutine we can now launch many other coroutines though
        fun enter() = runBlocking {
            info("Before counting")
            val count = counting()
            info("After counting [$count]")
            info("Before incrementing")
            val inc = increment().await()
            info("After incrementing [$inc]")
        }

        suspend fun counting(): Int = coroutineScope {
            val data = ConcurrentLinkedQueue<Int>()
            repeat(1000){ number ->
                launch {
                    // DON'T call Thread.Sleep, that would block all Coroutines on this thread
                    // call delay instead
                    delay(500)
                    data.add(number)
                    if(number == 500) info("Count number $number")
                    if(number == 999) info("Countnumber $number")
                }
            }

            info("I'll wait until everything has finished")
            data.size
        }

        fun CoroutineScope.increment(): Deferred<AtomicInteger> {
            return async {
                val acc = AtomicInteger(0)
                repeat(1000){ number ->
                    launch {
                        // DON'T call Thread.Sleep, that would block all Coroutines on this thread
                        // call delay instead
                        delay(500)
                        acc.incrementAndGet()
                        if(number == 500) info("Increment number $number")
                        if(number == 999) info("Increment number $number")
                    }
                }

                info("I'll wait until everything has finished")
                acc
            }
        }

    }
}


