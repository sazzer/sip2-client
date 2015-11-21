package uk.co.grahamcox.sip2.messages.serializer

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uk.co.grahamcox.sip2.messages.ResendMessage

/**
 * Unit Tests for the Resend Message Serializer
 */
class ResendMessageSerializerTest {
    /** The serializer to test */
    lateinit private var serializer: ResendMessageSerializer

    /**
     * Create a new serializer for each test
     */
    @Before
    fun setup() {
        serializer = ResendMessageSerializer()
    }

    /**
     * Test that the correct Message ID is returned
     */
    @Test
    fun testMessageId() {
        Assert.assertEquals("97", serializer.messageId)
    }

    /**
     * Test that there are no fixed fields returned
     */
    @Test
    fun testFixedFields() {
        Assert.assertEquals(listOf<String>(), serializer.buildFixedComponents(ResendMessage()))
    }
}