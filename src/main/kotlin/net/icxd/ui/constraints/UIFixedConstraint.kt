package net.icxd.ui.constraints

import org.lwjgl.glfw.GLFW.*

class UIFixedConstraint(val x: Int, val y: Int) : UIConstraint() {
    override fun apply() {
        val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor()) ?: throw RuntimeException("Video mode is null.")

        if (x < 0 || x > vidmode.width()) throw RuntimeException("X coordinate is out of bounds.")
        if (y < 0 || y > vidmode.height()) throw RuntimeException("Y coordinate is out of bounds.")
    }
}