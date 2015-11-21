package uk.co.grahamcox.sip2.messages.serializer

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uk.co.grahamcox.sip2.messages.SCStatusCode
import uk.co.grahamcox.sip2.messages.SCStatusMessage

/**
 * Unit tests for the SC Status Message Serializer
 */
class SCStatusMessageSerializerTest {
    /** The serializer to test */
    lateinit private var serializer: SCStatusMessageSerializer

    /**
     * Create a new serializer for each test
     */
    @Before
    fun setup() {
        serializer = SCStatusMessageSerializer()
    }

    /**
     * Test that the correct Message ID is returned
     */
    @Test
    fun testMessageId() {
        Assert.assertEquals("99", serializer.messageId)
    }

    /**
     * Test that the correct fixed fields are returned for a status of OK
     */
    @Test
    fun testStatusOK() {
        val message = SCStatusMessage(SCStatusCode.OK, 100)

        Assert.assertEquals(listOf("0", "100", "2.00"), serializer.buildFixedComponents(message))
    }

    /**
     * Test that the correct fixed fields are returned for a status of OUT_OF_PAPER
     */
    @Test
    fun testStatusOutOfPaper() {
        val message = SCStatusMessage(SCStatusCode.OUT_OF_PAPER, 100)

        Assert.assertEquals(listOf("1", "100", "2.00"), serializer.buildFixedComponents(message))
    }

    /**
     * Test that the correct fixed fields are returned for a status of SHUTDOWN
     */
    @Test
    fun testStatusShutdown() {
        val message = SCStatusMessage(SCStatusCode.SHUTDOWN, 100)

        Assert.assertEquals(listOf("2", "100", "2.00"), serializer.buildFixedComponents(message))
    }

    /**
     * Test that the print width is 0-padded if it's less than 3 digits long
     */
    @Test
    fun testShortPrintWidth() {
        val message = SCStatusMessage(SCStatusCode.OK, 1)

        Assert.assertEquals(listOf("0", "001", "2.00"), serializer.buildFixedComponents(message))
    }

}