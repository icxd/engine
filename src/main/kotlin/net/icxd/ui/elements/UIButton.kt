package net.icxd.ui.elements

import net.icxd.App
import net.icxd.events.PaintEvent
import net.icxd.ui.UIElement
import net.icxd.ui.constraints.UIConstraint
import net.icxd.ui.constraints.UIFixedConstraint
import org.lwjgl.opengl.GL11.*

class UIButton(private val positionConstraint: UIConstraint, private val sizeConstraint: UIConstraint) :
    UIElement(listOf(positionConstraint, sizeConstraint)) {
    enum class State { NORMAL, HOVER, PRESSED, DISABLED }

    private var state = State.NORMAL

    init {
        App.INSTANCE.eventHandler.subscribe { if (it is PaintEvent) paint(it) }
    }

    override fun paint(e: PaintEvent) {
        glBegin(GL_QUADS)

        when (state) {
            State.NORMAL -> glColor3f(0.5f, 0.5f, 0.5f)
            State.HOVER -> glColor3f(0.6f, 0.6f, 0.6f)
            State.PRESSED -> glColor3f(0.7f, 0.7f, 0.7f)
            State.DISABLED -> glColor3f(0.4f, 0.4f, 0.4f)
        }

        val x = (positionConstraint as UIFixedConstraint).x
        val y = positionConstraint.y
        val width = (sizeConstraint as UIFixedConstraint).x
        val height = sizeConstraint.y

        glVertex2i(x, y)
        glVertex2i(x + width, y)
        glVertex2i(x + width, y + height)
        glVertex2i(x, y + height)

        glEnd()
    }
}