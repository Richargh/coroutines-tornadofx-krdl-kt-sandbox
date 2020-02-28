package de.richargh.sandbox.cornado

import javafx.application.Application
import javafx.stage.Stage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tornadofx.App
import java.time.LocalDate

class Cornado: App() {
    override val primaryView = MainView::class

    private val events: Events by inject()

    override fun start(stage: Stage) = runBlocking {
        super.start(stage)

        events.gamePlayingNotification.push(Person("Samantha", "Stuart", LocalDate.of(1981, 12, 4)))
        events.gamePlayingNotification.push(Person("Tom", "Marks", LocalDate.of(2001, 1, 23)))
        events.gamePlayingNotification.push(Person("Stuart", "Gills", LocalDate.of(1989, 5, 23)))
        events.gamePlayingNotification.push(Person("Nicole", "Williams", LocalDate.of(1998, 8, 11)))

        repeat(10){ index ->
            launch {
                delay(100)
                info(index)
            }
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(Cornado::class.java, *args)
}