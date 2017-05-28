package minek.kotlin.everywehre.server

import minek.kotlin.everywhere.server.Container
import minek.kotlin.everywhere.server.Handler
import minek.kotlin.everywhere.server.f
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

    @Test
    fun testDoHandle() {
        val container = object : Container() {
            val increase = f<Int, Int>()
            val value = f<Unit, Int>()
            val sideEffect = f<Int, Unit>()

            init {
                increase { it + 1 }
                value { 1984 }
                sideEffect {}
            }
        }

        Assert.assertEquals("2", container.increase.doHandle("1"))
        Assert.assertEquals("1984", container.value.doHandle(""))
        Assert.assertEquals("", container.sideEffect.doHandle("1"))
    }
}
