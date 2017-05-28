package minek.kotlin.everywhere.server

abstract class Container {
    fun <P, R> f() = Handler<P, R>()
}