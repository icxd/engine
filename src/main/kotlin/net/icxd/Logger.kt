package net.icxd

import java.time.Instant

fun log(level: LogLevel, message: String) {
    val now = Instant.now()
    val nowFormatted = now.toString().substring(0, 19).replace("T", " ")
    println("($nowFormatted) [$level] $message")
}

enum class LogLevel(private val color: ConsoleColor) {
    DEBUG(ConsoleColor.CYAN),
    INFO(ConsoleColor.GREEN),
    WARN(ConsoleColor.YELLOW),
    ERROR(ConsoleColor.RED),
    FATAL(ConsoleColor.PURPLE);

    override fun toString(): String { return color.toString() + name + ConsoleColor.RESET }
}
