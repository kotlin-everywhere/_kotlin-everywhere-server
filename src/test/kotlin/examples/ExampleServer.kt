package examples

import minek.kotlin.everywhere.server.Container
import minek.kotlin.everywhere.server.f
import minek.kotlin.everywhere.server.runServer
import java.util.*

class Counter : Container() {
    val show = f<Unit, Int>()
    val increase = f<Int, Unit>()
}

class ExampleServer : Container() {
    val echo = f<String, String>()

    data class DateForm(val year: Int, val month: Int, val date: Int)

    val date = f<DateForm, Date>()

    val counter = Counter()
}


private var count = 0

fun Counter.impl() {
    show { count }
    increase { count += it }
}

fun ExampleServer.impl() {
    echo { it }
    date { Date(it.year - 1900, it.month, it.date) }

    counter.impl()
}

fun main(args: Array<String>) {
    ExampleServer()
            .apply(ExampleServer::impl)
            .runServer(8080)
}
