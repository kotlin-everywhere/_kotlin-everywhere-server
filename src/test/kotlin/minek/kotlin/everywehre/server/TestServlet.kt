package minek.kotlin.everywehre.server

import minek.kotlin.everywhere.server.Container
import minek.kotlin.everywhere.server.ContainerServlet
import minek.kotlin.everywhere.server.f
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import java.security.Principal
import java.util.*
import javax.servlet.*
import javax.servlet.http.*

class TestServlet {
    private class TestHttpServletRequest(private val uri: String, private val input: String) : HttpServletRequest {
        override fun isUserInRole(role: String?): Boolean {
            TODO("not implemented")
        }

        override fun startAsync(): AsyncContext {
            TODO("not implemented")
        }

        override fun startAsync(servletRequest: ServletRequest?, servletResponse: ServletResponse?): AsyncContext {
            TODO("not implemented")
        }

        override fun getPathInfo(): String {
            TODO("not implemented")
        }

        override fun getProtocol(): String {
            TODO("not implemented")
        }

        override fun getCookies(): Array<Cookie> {
            TODO("not implemented")
        }

        override fun getParameterMap(): MutableMap<String, Array<String>> {
            TODO("not implemented")
        }

        override fun getRequestURL(): StringBuffer {
            TODO("not implemented")
        }

        override fun getAttributeNames(): Enumeration<String> {
            TODO("not implemented")
        }

        override fun setCharacterEncoding(env: String?) {
            TODO("not implemented")
        }

        override fun getParameterValues(name: String?): Array<String> {
            TODO("not implemented")
        }

        override fun getRemoteAddr(): String {
            TODO("not implemented")
        }

        override fun isAsyncStarted(): Boolean {
            TODO("not implemented")
        }

        override fun getContentLengthLong(): Long {
            TODO("not implemented")
        }

        override fun getLocales(): Enumeration<Locale> {
            TODO("not implemented")
        }

        override fun getRealPath(path: String?): String {
            TODO("not implemented")
        }

        override fun login(username: String?, password: String?) {
            TODO("not implemented")
        }

        override fun getContextPath(): String {
            TODO("not implemented")
        }

        override fun isRequestedSessionIdValid(): Boolean {
            TODO("not implemented")
        }

        override fun getServerPort(): Int {
            TODO("not implemented")
        }

        override fun getAttribute(name: String?): Any {
            TODO("not implemented")
        }

        override fun getDateHeader(name: String?): Long {
            TODO("not implemented")
        }

        override fun getRemoteHost(): String {
            TODO("not implemented")
        }

        override fun getRequestedSessionId(): String {
            TODO("not implemented")
        }

        override fun getServletPath(): String {
            TODO("not implemented")
        }

        override fun getSession(create: Boolean): HttpSession {
            TODO("not implemented")
        }

        override fun getSession(): HttpSession {
            TODO("not implemented")
        }

        override fun getServerName(): String {
            TODO("not implemented")
        }

        override fun getLocalAddr(): String {
            TODO("not implemented")
        }

        override fun isSecure(): Boolean {
            TODO("not implemented")
        }

        override fun <T : HttpUpgradeHandler?> upgrade(handlerClass: Class<T>?): T {
            TODO("not implemented")
        }

        override fun isRequestedSessionIdFromCookie(): Boolean {
            TODO("not implemented")
        }

        override fun getPart(name: String?): Part {
            TODO("not implemented")
        }

        override fun getRemoteUser(): String {
            TODO("not implemented")
        }

        override fun getLocale(): Locale {
            TODO("not implemented")
        }

        override fun getMethod(): String {
            TODO("not implemented")
        }

        override fun isRequestedSessionIdFromURL(): Boolean {
            TODO("not implemented")
        }

        override fun getLocalPort(): Int {
            TODO("not implemented")
        }

        override fun isRequestedSessionIdFromUrl(): Boolean {
            TODO("not implemented")
        }

        override fun getServletContext(): ServletContext {
            TODO("not implemented")
        }

        override fun getQueryString(): String {
            TODO("not implemented")
        }

        override fun getDispatcherType(): DispatcherType {
            TODO("not implemented")
        }

        override fun getHeaders(name: String?): Enumeration<String> {
            TODO("not implemented")
        }

        override fun getUserPrincipal(): Principal {
            TODO("not implemented")
        }

        override fun getParts(): MutableCollection<Part> {
            TODO("not implemented")
        }

        override fun getReader(): BufferedReader {
            TODO("not implemented")
        }

        override fun getScheme(): String {
            TODO("not implemented")
        }

        override fun logout() {
            TODO("not implemented")
        }

        override fun getInputStream(): ServletInputStream {
            val stream = input.byteInputStream()
            return object : ServletInputStream() {
                override fun isReady(): Boolean {
                    return true
                }

                override fun isFinished(): Boolean {
                    return stream.available() == 0
                }

                override fun read(): Int {
                    return stream.read()
                }

                override fun setReadListener(readListener: ReadListener?) {
                    TODO("not implemented")
                }

            }
        }

        override fun getLocalName(): String {
            TODO("not implemented")
        }

        override fun isAsyncSupported(): Boolean {
            TODO("not implemented")
        }

        override fun getAuthType(): String {
            TODO("not implemented")
        }

        override fun getCharacterEncoding(): String {
            TODO("not implemented")
        }

        override fun getParameterNames(): Enumeration<String> {
            TODO("not implemented")
        }

        override fun authenticate(response: HttpServletResponse?): Boolean {
            TODO("not implemented")
        }

        override fun removeAttribute(name: String?) {
            TODO("not implemented")
        }

        override fun getPathTranslated(): String {
            TODO("not implemented")
        }

        override fun getContentLength(): Int {
            TODO("not implemented")
        }

        override fun getHeader(name: String?): String {
            TODO("not implemented")
        }

        override fun getIntHeader(name: String?): Int {
            TODO("not implemented")
        }

        override fun changeSessionId(): String {
            TODO("not implemented")
        }

        override fun getContentType(): String {
            TODO("not implemented")
        }

        override fun getAsyncContext(): AsyncContext {
            TODO("not implemented")
        }

        override fun getRequestURI(): String {
            return uri
        }

        override fun getRequestDispatcher(path: String?): RequestDispatcher {
            TODO("not implemented")
        }

        override fun getHeaderNames(): Enumeration<String> {
            TODO("not implemented")
        }

        override fun setAttribute(name: String?, o: Any?) {
            TODO("not implemented")
        }

        override fun getParameter(name: String?): String {
            TODO("not implemented")
        }

        override fun getRemotePort(): Int {
            TODO("not implemented")
        }

    }

    class TestHttpServletResponse : HttpServletResponse {
        internal var _contentType: String? = null
        private val stream = ByteArrayOutputStream()
        private val _printer = PrintWriter(stream)

        override fun encodeURL(url: String?): String {
            TODO("not implemented")
        }

        override fun encodeUrl(url: String?): String {
            TODO("not implemented")
        }

        override fun addIntHeader(name: String?, value: Int) {
            TODO("not implemented")
        }

        override fun addCookie(cookie: Cookie?) {
            TODO("not implemented")
        }

        override fun encodeRedirectUrl(url: String?): String {
            TODO("not implemented")
        }

        override fun flushBuffer() {
            TODO("not implemented")
        }

        override fun encodeRedirectURL(url: String?): String {
            TODO("not implemented")
        }

        override fun sendRedirect(location: String?) {
            TODO("not implemented")
        }

        override fun setBufferSize(size: Int) {
            TODO("not implemented")
        }

        override fun getLocale(): Locale {
            TODO("not implemented")
        }

        override fun sendError(sc: Int, msg: String?) {
            TODO("not implemented")
        }

        override fun sendError(sc: Int) {
            TODO("not implemented")
        }

        override fun setContentLengthLong(len: Long) {
            TODO("not implemented")
        }

        override fun setCharacterEncoding(charset: String?) {
            TODO("not implemented")
        }

        override fun addDateHeader(name: String?, date: Long) {
            TODO("not implemented")
        }

        override fun setLocale(loc: Locale?) {
            TODO("not implemented")
        }

        override fun getHeaders(name: String?): MutableCollection<String> {
            TODO("not implemented")
        }

        override fun addHeader(name: String?, value: String?) {
            TODO("not implemented")
        }

        override fun setContentLength(len: Int) {
            TODO("not implemented")
        }

        override fun getBufferSize(): Int {
            TODO("not implemented")
        }

        override fun resetBuffer() {
            TODO("not implemented")
        }

        override fun reset() {
            TODO("not implemented")
        }

        override fun setDateHeader(name: String?, date: Long) {
            TODO("not implemented")
        }

        override fun getStatus(): Int {
            TODO("not implemented")
        }

        override fun getCharacterEncoding(): String {
            TODO("not implemented")
        }

        override fun isCommitted(): Boolean {
            TODO("not implemented")
        }

        override fun setStatus(sc: Int) {
            TODO("not implemented")
        }

        override fun setStatus(sc: Int, sm: String?) {
            TODO("not implemented")
        }

        override fun getHeader(name: String?): String {
            TODO("not implemented")
        }

        override fun getContentType(): String {
            TODO("not implemented")
        }

        override fun getWriter(): PrintWriter {
            return _printer
        }

        internal val content: String
            get() {
                _printer.flush()
                return stream.toString("UTF-8")
            }

        override fun containsHeader(name: String?): Boolean {
            TODO("not implemented")
        }

        override fun setIntHeader(name: String?, value: Int) {
            TODO("not implemented")
        }

        override fun getHeaderNames(): MutableCollection<String> {
            TODO("not implemented")
        }

        override fun setHeader(name: String?, value: String?) {
            TODO("not implemented")
        }

        override fun getOutputStream(): ServletOutputStream {
            TODO("not implemented")
        }

        override fun setContentType(type: String?) {
            _contentType = type
        }

    }

    @Test
    fun testDoPost() {
        val container = object : Container() {
            val echo = f<String, String>()

            init {
                echo { it }
            }
        }

        val servlet = ContainerServlet(container)
        val resp = TestHttpServletResponse()
        servlet.doHandle(TestHttpServletRequest("/echo", "kotlin"), resp)

        assertEquals("application/json", resp._contentType)
        assertEquals("\"kotlin\"", resp.content)
    }
}
