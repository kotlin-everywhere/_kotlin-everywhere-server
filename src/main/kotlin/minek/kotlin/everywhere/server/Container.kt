package minek.kotlin.everywhere.server

abstract class Container {
    private val handlers: Map<String, Handler<*, *>> by lazy {
        this::class.java
                .methods
                .flatMap {
                    if (it.parameterCount != 0 && !it.name.startsWith("get")) {
                        listOf()
                    } else if (it.returnType === Handler::class.java) {
                        listOf(it.name.toPropertyName() to (it.invoke(this@Container) as Handler<*, *>))
                    } else if (Container::class.java.isAssignableFrom(it.returnType)) {
                        (it.invoke(this@Container) as Container).handlers
                                .map { entry -> "${it.name.toPropertyName()}/${entry.key}" to entry.value }
                    } else {
                        listOf()
                    }
                }
                .toMap()
    }

    internal fun findHandler(path: String): Handler<*, *>? {
        return handlers[path]
    }

    private fun String.toPropertyName(): String {
        return this.substring(3, 4).toLowerCase() + this.substring(4)
    }
}
