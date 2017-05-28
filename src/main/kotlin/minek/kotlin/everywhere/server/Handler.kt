package minek.kotlin.everywhere.server

class Handler<P, R> {
    var handler: (P) -> R = { throw NotImplemented() }
        private set

    operator fun invoke(handler: (P) -> R) {
        this.handler = handler
    }

    class NotImplemented : Throwable()
}

