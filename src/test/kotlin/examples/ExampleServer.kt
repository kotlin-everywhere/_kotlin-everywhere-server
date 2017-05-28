package examples

import minek.kotlin.everywhere.server.Container
import minek.kotlin.everywhere.server.f
import minek.kotlin.everywhere.server.runServer
import java.util.*

class ExampleServer : Container() {
    val echo = f<String, String>()
    val showCount = f<Unit, Int>()
    val increment = f<Int, Unit>()
    val date = f<DateForm, Date>()

    data class DateForm(val year: Int, val month: Int, val date: Int)
}

var count = 0

fun ExampleServer.impl() {
    echo { it }
    showCount { count }
    increment { count += it }
    date { Date(it.year - 1900, it.month, it.date) }
}

fun main(args: Array<String>) {
    ExampleServer()
            .apply(ExampleServer::impl)
            .runServer(8080)
}
