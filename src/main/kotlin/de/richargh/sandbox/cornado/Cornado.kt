package de.richargh.sandbox.cornado

import javafx.application.Application
import javafx.stage.Stage
import kotlinx.coroutines.runBlocking
import tornadofx.App

class Cornado: App() {
    override val primaryView = MainView::class

    override fun start(stage: Stage) = runBlocking {
        super.start(stage)
    }
}

fun main(args: Array<String>) {
    Application.launch(Cornado::class.java, *args)
}