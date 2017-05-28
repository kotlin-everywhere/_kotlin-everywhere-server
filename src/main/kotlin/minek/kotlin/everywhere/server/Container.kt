package minek.kotlin.everywhere.server

abstract class Container {
    private var initialized = false
    fun <P, R> f() = Handler<P, R>()

    companion object {
        fun initialize(container: Container) {
            container.initialized = true
        }
    }
}