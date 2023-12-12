package net.icxd.ui

import net.icxd.events.EventHandler
import net.icxd.events.PaintEvent

abstract class UIElement {
    abstract fun paint(e: PaintEvent)

    fun draw(window: Long, handler: EventHandler) {
        val event = PaintEvent(window)
        handler.handle(event)
        paint(event)
    }
}