package de.richargh.sandbox.aminisample

import de.richargh.sandbox.cornado.info
import javafx.application.Application
import javafx.beans.property.SimpleDoubleProperty
import kotlinx.coroutines.*
import tornadofx.*

fun main(args: Array<String>) {
    Application.launch(TornadoFXApp::class.java, *args)
}

class TornadoFXApp: App(FunView::class)

class FunView: CoroutineScope, View() {

    override val coroutineContext = SupervisorJob() +
                                    Dispatchers.Default +
                                    CoroutineName(javaClass.simpleName)

    private val alist = mutableListOf<String>().asObservable()

    override val root = vbox {

        // first textfield
        hbox {
            val nameField = textfield()
            val completion = SimpleDoubleProperty(0.0)
            button("Non-Blocking") {
                enableWhen(nameField.textProperty().isNotEmpty)
                action {
                    info("Before launch")
                    completion.value = 0.2
                    launch {
                        val element = nameField.text
                        info("Before Heavy Computation")
                        delay(500); completion.value = 0.4
                        delay(500); completion.value = 0.6
                        withContext(Dispatchers.Main) {
                            delay(500); completion.value = 0.8
                            info("Adding $element to list")
                            alist.add(element)
                            delay(500); completion.value = 1.0
                            delay(500); completion.value = 0.0
                        }
                        info("After Heavy Computation")
                    }
                    info("After launch")
                }
            }
            progressindicator(completion)
        }

        listview(alist)
    }
}