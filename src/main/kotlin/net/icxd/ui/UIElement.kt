package net.icxd.ui

import net.icxd.events.PaintEvent
import net.icxd.ui.constraints.UIConstraint

abstract class UIElement(private val constraints: List<UIConstraint>) {
    abstract fun paint(e: PaintEvent)
}