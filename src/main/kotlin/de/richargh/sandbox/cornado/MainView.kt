package de.richargh.sandbox.cornado

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import tornadofx.*
import java.time.LocalDate

class MainView: View() {

    private val events: Events by inject()

    var firstNameField: TextField by singleAssign()
    var lastNameField: TextField by singleAssign()
    var ageField: TextField by singleAssign()

    private val persons = mutableListOf<Person>().asObservable()

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
                events.gamePlayingNotification.push(
                        Person(firstNameField.text, lastNameField.text, LocalDate.of(2020, 8, 11)))
            }
        }
        val completion = SimpleDoubleProperty(0.5)
        progressindicator(completion) {}

        tableview(persons) {
            column("First Name", Person::firstnameProperty)
            column("Second Name", Person::lastnameProperty)
            column("Birthday", Person::birthdayProperty)
            column("Age", Person::ageProperty).cellFormat {
                text = it.toString()
                style {
                    backgroundColor += if (it != null && it < 18) {
                        c("#8b0000")
                    } else {
                        Color.TRANSPARENT
                    }
                }
            }
        }
    }

    init {
        events.gamePlayingNotification.subscribe {
            println("Game Playing")
            persons.add(it)
        }
    }
}