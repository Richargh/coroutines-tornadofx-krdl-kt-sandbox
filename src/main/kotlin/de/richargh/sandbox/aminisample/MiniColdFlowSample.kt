package de.richargh.sandbox.aminisample

import de.richargh.sandbox.cornado.info
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MiniColdFlowSample {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            info("Before creation")
            // you can create a flow anywhere you want and it will return immediately
            val flow = createFlow()
            info("After creation")

            // to consume a flow you need to enter coroutineland though
            val greeting: List<String> = runBlocking(Dispatchers.Default) {
                info("Consuming flow")
                flow.map {
                    when(it){
                        'H' -> "Hello"
                        'W' -> "World"
                        else -> "!"
                    }
                }.toList()
            }
            println(greeting)
        }

        fun createFlow(): Flow<Char> {
            val chars = flow {
                emit('H')
                emit('W')
            }
            return chars
        }
    }
}

