package net.icxd.ui.constraints

abstract class UIConstraint {
    abstract fun apply()

    companion object {
        fun fixed(x: Int, y: Int): UIFixedConstraint { return UIFixedConstraint(x, y) }
    }

    fun apply(constraints: List<UIConstraint>) { constraints.forEach { it.apply() } }
    fun apply(vararg constraints: UIConstraint) { constraints.forEach { it.apply() } }
}