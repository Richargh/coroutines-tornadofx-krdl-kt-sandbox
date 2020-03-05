package de.richargh.sandbox.cornado

import tornadofx.View
import tornadofx.borderpane

class MainView: View() {

    override val root = borderpane {
        top<Header>()
        left<CustomerForm>()
        center<CenterView>()
    }
}