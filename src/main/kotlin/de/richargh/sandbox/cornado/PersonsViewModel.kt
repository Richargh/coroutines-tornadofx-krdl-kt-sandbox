package de.richargh.sandbox.cornado

import tornadofx.asObservable

class PersonsViewModel {

    val items = mutableListOf<Person>().asObservable()

    fun add(person: Person){
        items.add(person)
    }

}