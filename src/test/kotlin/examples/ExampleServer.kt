package examples

import com.google.gson.GsonBuilder
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler
import org.eclipse.jetty.servlet.ServletHolder
import java.util.*
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KProperty

private val gson = GsonBuilder().create()

abstract class Container {
    private val endpoints = hashMapOf<String, Endpoint<*, *>>()

    internal fun addEndpoint(name: String, endpoint: Endpoint<*, *>) {
        endpoints.put(name, endpoint)
    }

    internal fun handle(req: HttpServletRequest, resp: HttpServletResponse) {
        val name = req.requestURI.substring(1)
        val endpoint = endpoints[name]
        if (endpoint == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND)
            return
        }

        endpoint.handle(req, resp)
    }
}

fun <T : Container> T.runServer(): T {
    val server = Server(8080)
    val handler = ServletHandler()
    server.handler = handler
    handler.addServletWithMapping(
            ServletHolder(object : HttpServlet() {
                override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
                    this@runServer.handle(req, resp)
                }
            }),
            "/*"
    )
    server.start()
    server.join()
    return this
}

inline fun <reified P : Any, R : Any> f(): EndpointDelegate<P, R> {
    return EndpointDelegate(P::class.java)
}

class EndpointDelegate<P : Any, R : Any>(private val parameterClass: Class<P>) {
    private var endpoint: Endpoint<P, R>? = null
    operator fun getValue(container: Container, property: KProperty<*>): Endpoint<P, R> {
        val endpoint = this.endpoint
        if (endpoint != null) {
            return endpoint
        }

        return Endpoint<P, R>(parameterClass).apply {
            this@EndpointDelegate.endpoint = this
            container.addEndpoint(property.name, this)
        }
    }
}

class Endpoint<P, R>(private val parameterClass: Class<P>) {
    private var handler: ((P) -> R)? = null

    operator fun invoke(handler: (P) -> R) {
        this.handler = handler
    }

    fun handle(req: HttpServletRequest, resp: HttpServletResponse) {
        val parameter =
                if (parameterClass === Unit.javaClass) {
                    @Suppress("UNCHECKED_CAST")
                    Unit as P
                } else {
                    gson.fromJson(req.inputStream.reader(), parameterClass)
                }
        val response = handler!!.invoke(parameter)
        resp.contentType = "application/json"
        if (response !== Unit) {
            gson.toJson(response, resp.writer)
        }
    }
}

class ExampleServer : Container() {
    val echo by f<String, String>()
    val showCount by f<Unit, Int>()
    val increment by f<Int, Unit>()
    val date by f<DateForm, Date>()

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
            .runServer()
}
