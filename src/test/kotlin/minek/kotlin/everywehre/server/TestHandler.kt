package minek.kotlin.everywehre.server

import minek.kotlin.everywhere.server.Container
import minek.kotlin.everywhere.server.Handler
import org.junit.Assert
import org.junit.Test

class TestHandler {
    @Test
    fun testHandlerInvocation() {
        val container = object : Container() {
            val echo = f<String, String>()

            init {
                echo { it }
            }
        }

        // Test echo handler
        val helloKotlin = "Hello, Kotlin!"
        Assert.assertEquals(helloKotlin, container.echo.handler(helloKotlin))
    }

    @Test(expected = Handler.NotImplemented::class)
    fun testNotImplemented() {
        val container = object : Container() {
            val dummy = f<Unit, Unit>()
        }

        container.dummy.handler(Unit)
    }
}
