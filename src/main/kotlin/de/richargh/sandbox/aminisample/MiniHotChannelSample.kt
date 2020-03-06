package de.richargh.sandbox.aminisample

import de.richargh.sandbox.cornado.info
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select

class MiniHotChannelSample {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking<Unit> {
            val trick = trick()
            val track = track()
            consume(trick, track)
        }

        fun CoroutineScope.trick() = produce<String> {
            while (true) { // sends "Trick" every 300 ms
                delay(300)
                send("Trick")
            }
        }

        @ExperimentalCoroutinesApi
        fun CoroutineScope.track() = produce<String> {
            while (true) { // sends "Track" every 500 ms
                delay(500)
                send("Track")
            }
        }

        fun CoroutineScope.consume(trick: ReceiveChannel<String>, track: ReceiveChannel<String>) = launch {
            repeat(7) {
                select<Unit> {
                    trick.onReceive { value ->
                        // this is the first select clause
                        info("'$value'")
                    }
                    track.onReceive { value ->
                        // this is the second select clause
                        info("'$value'")
                    }
                }
            }
        }
    }
}

