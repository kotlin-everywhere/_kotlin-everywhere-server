package minek.kotlin.everywhere.server

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler
import org.eclipse.jetty.servlet.ServletHolder

fun <T : Container> T.runServer(port: Int): T {
    val server = Server(port)
    val handler = ServletHandler()
    server.handler = handler
    handler.addServletWithMapping(ServletHolder(ContainerServlet(this)), "/*")
    server.start()
    server.join()
    return this
}

