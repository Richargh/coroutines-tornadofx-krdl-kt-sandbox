package de.richargh.sandbox.aminisample

import de.richargh.sandbox.cornado.*
import javafx.application.Application
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.TextField
import kotlinx.coroutines.*
import tornadofx.*
import java.time.LocalDate

fun main(args: Array<String>) {
    Application.launch(TornadoFXApp::class.java, *args)
}

class TornadoFXApp : App(FunView::class)

class FunView: CoroutineScope, View() {

    override val coroutineContext = SupervisorJob() +
                                    Dispatchers.Default +
                                    CoroutineName(javaClass.simpleName)

    private val api : Rest by inject()

    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()
    var ageField: TextField by singleAssign()

    private val greekLetters = mutableListOf("Alpha","Beta",
                              "Gamma","Delta","Epsilon").asObservable()

    override val root = vbox {

        hbox {
            label("First Name")
            firstNameField = textfield()
        }
        hbox {
            label("Last Name")
            lastNameField = textfield()
        }
        hbox {
            label("Age")
            ageField = textfield()
        }
        button("ADD") {
            useMaxWidth = true
            action {
                info("On Add")
                launch {
                    info("Heavy Computation")
                    delay(2000)
                    val person = PersonViewModel(firstNameField.text, lastNameField.text, LocalDate.of(2020, 8, 11))
                    withContext(Dispatchers.Main){
                        info("Pushing Event")
                        greekLetters.add("foo")
                    }
                }
            }
        }
        val completion = SimpleDoubleProperty(0.5)
        progressindicator(completion) {}

        listview(greekLetters)
    }
}