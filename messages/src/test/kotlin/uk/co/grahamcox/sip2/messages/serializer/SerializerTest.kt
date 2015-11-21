package uk.co.grahamcox.sip2.messages.serializer

import org.junit.Assert
import org.junit.Test
import uk.co.grahamcox.sip2.messages.ResendMessage

/**
 * Unit tests for the Serializer class
 */
class SerializerTest {
    /**
     * Serialize a Resend Message into it's string form.
     * This message has no components at all, and so should serialize into simply the string "97"
     */
    @Test
    fun serializeResendMessage() {
        val serializer = Serializer()
        val message = ResendMessage()
        val expected = "97"

        val serializedMessage = serializer.serialize(message)
        Assert.assertEquals(expected, serializedMessage)
    }
}