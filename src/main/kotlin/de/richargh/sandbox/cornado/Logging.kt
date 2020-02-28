package de.richargh.sandbox.cornado

fun info(message: Any?) = println("[${Thread.currentThread().name}] $message")