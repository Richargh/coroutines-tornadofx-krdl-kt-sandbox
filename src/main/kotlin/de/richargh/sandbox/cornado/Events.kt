package de.richargh.sandbox.cornado

import org.reactfx.EventSource
import tornadofx.Component
import tornadofx.ScopedInstance

class Events: Component(), ScopedInstance {
    val personAdded = EventSource<Person>()

    init {
        personAdded.subscribe { info("Notification: Person added") }
    }
}