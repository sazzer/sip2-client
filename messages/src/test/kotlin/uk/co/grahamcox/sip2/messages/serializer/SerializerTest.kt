package uk.co.grahamcox.sip2.messages.serializer

import org.junit.Assert
import org.junit.Test
import uk.co.grahamcox.sip2.messages.ResendMessage
import uk.co.grahamcox.sip2.messages.SCStatusCode
import uk.co.grahamcox.sip2.messages.SCStatusMessage

/**
 * Unit tests for the Serializer class
 */
class SerializerTest {
    /**
     * Serialize a message of an unsupported type
     */
    @Test(expected = IllegalArgumentException::class)
    fun serializeUnknownMessage() {
        val serializer = Serializer(mapOf())
        val message = ResendMessage()

        serializer.serialize(message)
    }

    /**
     * Serialize a Resend Message into it's string form.
     * This message has no components at all, and so should serialize into simply the string "97"
     */
    @Test
    fun serializeResendMessage() {
        val serializer = Serializer(mapOf(ResendMessage::class.java to ResendMessageSerializer()))
        val message = ResendMessage()
        val expected = "97"

        val serializedMessage = serializer.serialize(message)
        Assert.assertEquals(expected, serializedMessage)
    }
}