package ui

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*


@ExtendWith(MockKExtension::class)
class MenuTest {

    @InjectMockKs
    lateinit var menu: Menu

    @Test
    fun `Given an input then must accept user input then return`() {

        val input = "06 00"
        val `in`: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(`in`)

        val result = menu.userMenu()

        assertEquals(input, result)
    }

}