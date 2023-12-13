package net.icxd

import net.icxd.events.EventHandler
import net.icxd.events.PaintEvent
import net.icxd.ui.constraints.UIConstraint
import net.icxd.ui.constraints.UIFixedConstraint
import net.icxd.ui.elements.UIButton
import org.lwjgl.glfw.Callbacks.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.*
import org.lwjgl.system.MemoryUtil.*
import java.lang.Exception
import java.lang.RuntimeException

object App {
    @JvmStatic
    val INSTANCE: App
        get() = this

    val eventHandler = EventHandler()
    private var window: Long = 0;

    @JvmStatic
    fun main(args: Array<String>) {
        log(LogLevel.INFO, "Hello, world!")

        init()
        loop()

        glfwFreeCallbacks(window)
        glfwDestroyWindow(window)

        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }

    private fun init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw IllegalStateException("Unable to initialize GLFW")

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)

        window = glfwCreateWindow(960, 540, "Game", NULL, NULL)
        if (window == NULL)
            throw RuntimeException("Failed to create the GLFW window")

        glfwSetKeyCallback(window, fun (window, key, scancode, action, mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true)
        })

        try  {
            val stack = stackPush()
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            glfwGetWindowSize(window, pWidth, pHeight)

            val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor()) ?: throw RuntimeException("Video mode is null.")
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2)
        } catch (e: Exception) { e.printStackTrace() }

        glfwMakeContextCurrent(window)
        glfwSwapInterval(1)

        glfwShowWindow(window)
    }

    private fun loop() {
        createCapabilities()

        glClearColor(0.3f, 0.2f, 0.3f, 0.0f)

        val btn = UIButton(UIConstraint.fixed(0, 0), UIConstraint.fixed(100, 100))

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

            eventHandler.notify(PaintEvent(window))

            glfwSwapBuffers(window)
            glfwPollEvents()
        }
    }
}