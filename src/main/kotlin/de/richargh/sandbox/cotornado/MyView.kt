package de.richargh.sandbox.cotornado

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.TextField
import tornadofx.*

class MyView : View() {

    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()

    override val root = vbox {
        hbox {
            label("First Name")
            firstNameField = textfield()
        }
        hbox {
            label("Last Name")
            lastNameField = textfield()
        }
        button("LOGIN") {
            useMaxWidth = true
            action {
                println("Logging in as ${firstNameField.text} ${lastNameField.text}")
            }
        }
        val completion = SimpleDoubleProperty(0.5)
        progressindicator(completion) {}
    }
}