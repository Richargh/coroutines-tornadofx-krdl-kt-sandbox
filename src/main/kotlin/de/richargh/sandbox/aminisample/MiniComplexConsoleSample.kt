package de.richargh.sandbox.aminisample

import de.richargh.sandbox.cornado.info
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class MiniComplexConsoleSample {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            compute("What about whatism?")
            val fooTitle = searchTitleAsync("Foo")
            val fubarTitle = searchTitleAsync("Fubar")

            info("Foo is [${fooTitle.await()}]")
            info("Foo is [${fubarTitle.await()}]")
        }

        fun CoroutineScope.compute(question: String) = launch(Dispatchers.Default) {
            info("Before Heavy computation")
            delay(2000)
            info("The answer is always [42]")
        }

        fun CoroutineScope.searchTitleAsync(term: String): Deferred<String?> {
            return async(Dispatchers.IO) {
                info("Searching")
                val response: String? = try {
                    URL("https://en.wikipedia.org/w/index.php?search=$term")
                            .openStream()
                            .bufferedReader()
                            .use { it.readText() }
                } catch (ex: IOException){
                    info("Request failed: $ex")
                    null
                }

                response?.split("<title>")?.get(1)?.split("</title>")?.get(0)
            }
        }
    }
}
