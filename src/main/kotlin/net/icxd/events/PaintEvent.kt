package net.icxd.events

class PaintEvent(private val window: Long) : Event() {
    override fun toString(): String { return "PaintEvent(window=$window)" }
}