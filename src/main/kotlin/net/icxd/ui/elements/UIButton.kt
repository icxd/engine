package net.icxd.ui.elements

import net.icxd.events.PaintEvent
import net.icxd.ui.UIElement
import org.lwjgl.opengl.GL11.*

class UIButton : UIElement() {
    // TODO: button state.

    override fun paint(e: PaintEvent) {
        glBegin(GL_QUADS)

        glColor3f(1.0f, 0.0f, 0.0f)
        glVertex2f(-0.5f, 0.5f)

        glColor3f(1.0f, 1.0f, 0.0f)
        glVertex2f(0.5f, 0.5f)

        glColor3f(0.0f, 1.0f, 0.0f)
        glVertex2f(0.5f, -0.5f)

        glColor3f(0.0f, 0.0f, 1.0f)
        glVertex2f(-0.5f, -0.5f)

        glEnd()
    }
}