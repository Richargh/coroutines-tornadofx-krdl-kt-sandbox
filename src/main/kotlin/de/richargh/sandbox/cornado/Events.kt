package de.richargh.sandbox.cornado

import org.reactfx.EventSource
import tornadofx.Component
import tornadofx.ScopedInstance

class Events: Component(), ScopedInstance {
    val gamePlayingNotification = EventSource<Person>()

    init {
        gamePlayingNotification.subscribe { info("Notification: Game playing") }
    }
}