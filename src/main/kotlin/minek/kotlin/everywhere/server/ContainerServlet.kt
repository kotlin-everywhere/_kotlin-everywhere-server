package minek.kotlin.everywhere.server

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ContainerServlet(private val container: Container) : HttpServlet() {
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        doHandle(req, resp)
    }

    internal fun doHandle(req: HttpServletRequest, resp: HttpServletResponse) {
        val handler = container.findHandler(req.requestURI.substring(1))
        if (handler == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND)
            return
        }

        resp.contentType = "application/json"
        resp.writer.write(handler.doHandle(req.inputStream.reader().readText()))
        resp.writer.close()
    }
}