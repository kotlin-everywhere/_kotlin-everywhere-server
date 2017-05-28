package minek.kotlin.everywhere.server

import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaGetter

abstract class Container {
    private val handlers: Map<String, Handler<*, *>> by lazy {
        this::class
                .memberProperties
                .filter { it.javaGetter?.returnType == Handler::class.java }
                .map {
                    @Suppress("UNCHECKED_CAST")
                    it.name to (it as KProperty1<Container, Handler<*, *>>).invoke(this@Container)
                }
                .toMap()
    }

    internal fun findHandler(path: String): Handler<*, *>? {
        return handlers[path]
    }
}
