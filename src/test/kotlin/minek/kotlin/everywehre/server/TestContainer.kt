package minek.kotlin.everywehre.server

import minek.kotlin.everywhere.server.Container
import minek.kotlin.everywhere.server.f
import org.junit.Assert.assertEquals
import org.junit.Test

class TestContainer {
    @Test
    fun testRouter() {
        val container = object : Container() {
            val echo = f<String, String>()

            init {
                echo { it }
            }
        }

        assertEquals(null, container.findHandler("/"))
        assertEquals(null, container.findHandler("/echo"))
        assertEquals(container.echo, container.findHandler("echo"))
    }
}

