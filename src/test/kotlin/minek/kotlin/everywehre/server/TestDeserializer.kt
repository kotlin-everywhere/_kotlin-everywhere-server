package minek.kotlin.everywehre.server

import com.google.gson.GsonBuilder
import minek.kotlin.everywhere.server.f
import org.junit.Assert.assertEquals
import org.junit.Test

class TestDeserializer {

    private fun toJson(obj: Any): String {
        return GsonBuilder().create().toJson(obj)
    }

    data class Model<out T>(val value: T)

    @Test
    fun test() {
        assertEquals("string", f<String, Unit>().deserialize("\"string\""))
        assertEquals(listOf("string"), f<List<String>, Unit>().deserialize("[\"string\"]"))


        val model = Model(1)
        assertEquals(model, f<Model<Int>, Unit>().deserialize(toJson(model)))
    }
}