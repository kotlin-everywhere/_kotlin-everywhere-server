package minek.kotlin.everywhere.server

import com.google.gson.GsonBuilder

class Handler<P, R>(private val parameterClass: Class<P>) {
    var handler: (P) -> R = { throw NotImplemented() }
        private set

    operator fun invoke(handler: (P) -> R) {
        this.handler = handler
    }

    internal fun doHandle(json: String): String {
        @Suppress("UNCHECKED_CAST")
        val parameter =
                if (parameterClass == Unit.javaClass) Unit as P
                else gson.fromJson(json, parameterClass)
        return handler(parameter).let {
            when (it) {
                is Unit -> ""
                else -> gson.toJson(it)
            }
        }
    }

    internal class NotImplemented : Throwable()
}

inline fun <reified P, R> f() = Handler<P, R>(P::class.java)

private val gson = GsonBuilder().create()
