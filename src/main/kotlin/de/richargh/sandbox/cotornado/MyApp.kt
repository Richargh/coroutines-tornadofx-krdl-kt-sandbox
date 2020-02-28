package de.richargh.sandbox.cotornado

import javafx.application.Application
import tornadofx.App

class TableViewApp : App() {
    override val primaryView = MyView::class
}

fun main(args: Array<String>) {
    Application.launch(TableViewApp::class.java, *args)
}