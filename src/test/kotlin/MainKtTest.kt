import com.github.stefanbirkner.systemlambda.SystemLambda.*
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import model.ClockAngle
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import service.ClockService
import ui.Menu
import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class MainKtTest {

    @MockK
    lateinit var clockService: ClockService

    @MockK
    lateinit var menu: Menu

    @Test
    fun `main method should return a proper angle`() {

        val clockAngle = ClockAngle(angle = 90, hourAngle = 90, minAngle = 90)
        val input = "06:00"
        every { menu.userMenu() } returns input
        every { clockService.handleHourAndMinute(input) } returns clockAngle

        val output = tapSystemOut {
            main(arrayOf("06:00"))
        }

        assertEquals(
            "Hour angle is 90, Minute angle is 0",
            output.trim()
        )
    }

    @Test
    fun `main method no arguments should return a proper angle`() {

        val clockAngle = ClockAngle(angle = 90, hourAngle = 90, minAngle = 90)
        val input = "06:00"
        every { menu.userMenu() } returns input
        every { clockService.handleHourAndMinute(input) } returns clockAngle

        val `in`: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(`in`)

        val output = tapSystemOutNormalized {
            main(emptyArray())
        }

        val message = "Allowed format: \n" +
                "HH MM (ex: 08 15 or 14 30)\n" +
                "HH:MM (ex: 08:15 or 14:30)\n" +
                "HH-MM (ex: 08-15 or 14-30)\n" +
                "HH/MM (ex: 08/15 or 14/30)\n" +
                "HHˆMM (ex: 08ˆ15 or 14ˆ30)\n" +
                "HH;MM (ex: 08;15 or 14;30)\n" +
                "Enter hour and minute information: \n" +
                "Hour angle is 90, Minute angle is 0".trim()

        assertEquals(
            message,
            output.trim()
        )
    }
}