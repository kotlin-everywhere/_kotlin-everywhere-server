@file:Suppress("unused")

package minek.kotlin.everywhere.server

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Handler<P, R>(private val parameterClass: Class<P>, private val type: Type) {

    var handler: (P) -> R = { throw NotImplemented() }
        private set

    operator fun invoke(handler: (P) -> R) {
        this.handler = handler
    }

    internal fun doHandle(json: String): String {
        val parameter = deserialize(json)
        return handler(parameter).let {
            when (it) {
                is Unit -> ""
                else -> gson.toJson(it)
            }
        }
    }

    internal fun deserialize(json: String): P {
        @Suppress("UNCHECKED_CAST")
        return if (parameterClass == Unit.javaClass) Unit as P else gson.fromJson(json, type)
    }

    internal class NotImplemented : Throwable()
}

inline fun <reified P : Any, R> f(): Handler<P, R> {
    return Handler(P::class.java, object : TypeToken<P>() {}.type)
}

private val gson = GsonBuilder().create()
