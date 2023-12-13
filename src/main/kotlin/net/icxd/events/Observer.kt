package net.icxd.events

typealias Observer = (Event) -> Unit

class EventHandler {
    private val observers = mutableListOf<Observer>()

    fun subscribe(observer: Observer) {
        observers.add(observer)
    }

    fun unsubscribe(observer: Observer) {
        observers.remove(observer)
    }

    fun notify(event: Event) {
        observers.forEach { it(event) }
    }
}