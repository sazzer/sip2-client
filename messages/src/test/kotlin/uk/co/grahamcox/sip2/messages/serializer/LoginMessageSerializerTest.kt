package uk.co.grahamcox.sip2.messages.serializer

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uk.co.grahamcox.sip2.messages.LoginMessage

/**
 * Unit tests for the Login Message Serializer
 */
class LoginMessageSerializerTest {
    /** The serializer to test */
    lateinit private var serializer: LoginMessageSerializer

    /**
     * Create a new serializer for each test
     */
    @Before
    fun setup() {
        serializer = LoginMessageSerializer()
    }

    /**
     * Test that the correct Message ID is returned
     */
    @Test
    fun testMessageId() {
        Assert.assertEquals("93", serializer.messageId)
    }

    /**
     * Test that the correct Fixed Fields are returned
     */
    @Test
    fun testFixedFields() {
        val message = LoginMessage(
                userIdAlgorithm = "0",
                passwordAlgorithm = "1",
                userId = "graham",
                password = "password",
                location = "Office"
        )

        Assert.assertEquals(listOf("0", "1"), serializer.buildFixedComponents(message))
    }

    /**
     * Test that the correct Variable Fields are returned when they are all populated
     */
    @Test
    fun testAllVariableFields() {
        val message = LoginMessage(
                userIdAlgorithm = "0",
                passwordAlgorithm = "1",
                userId = "graham",
                password = "password",
                location = "Office"
        )

        Assert.assertEquals(listOf(
                "CN" to "graham",
                "CO" to "password",
                "CP" to "Office"
        ),
                serializer.buildVariableComponents(message))
    }

    /**
     * Test that the correct Variable Fields are returned when they are all empty
     */
    @Test
    fun testNoVariableFields() {
        val message = LoginMessage(
                userIdAlgorithm = "0",
                passwordAlgorithm = "1",
                userId = null,
                password = null,
                location = null
        )

        Assert.assertEquals(emptyList<Pair<String, String>>(), serializer.buildVariableComponents(message))
    }

}