package uk.co.grahamcox.sip2.messages.serializer

import org.easymock.EasyMock
import org.easymock.EasyMockSupport
import org.easymock.Mock
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uk.co.grahamcox.sip2.messages.Message

/**
 * Unit tests for the Serializer class
 */
class SerializerTest : EasyMockSupport() {
    /** The message we're testing with */
    class FakeMessage : Message

    /** The message to serialize */
    val message = FakeMessage()

    /** The mock message serializer to use */
    @Mock
    lateinit private var mockMessageSerializer: MessageSerializer<FakeMessage>

    /** The test subject */
    lateinit private var testSubject: Serializer

    /**
     * Set up the test subject to use a mock Message Serializer to serialize a FakeMessage
     */
    @Before
    fun setupMocks() {
        injectMocks(this)
        testSubject = Serializer(mapOf(FakeMessage::class.java to mockMessageSerializer))
    }

    /**
     * Verify the mocks
     */
    @After
    fun verify() {
        verifyAll()
    }

    /**
     * Serialize a message of an unsupported type
     */
    @Test(expected = IllegalArgumentException::class)
    fun serializeUnknownMessage() {
        replayAll()

        val serializer = Serializer(mapOf())

        serializer.serialize(message)
    }

    /**
     * Serialize a message that has no fixed or variable fields
     */
    @Test
    fun serializeMessageNoFields() {
        EasyMock.expect(mockMessageSerializer.messageId)
                .andReturn("97")
        EasyMock.expect(mockMessageSerializer.buildFixedComponents(message))
                .andReturn(emptyList())
        EasyMock.expect(mockMessageSerializer.buildVariableComponents(message))
                .andReturn(emptyList())
        replayAll()

        val expected = "97"

        val serializedMessage = testSubject.serialize(message)
        Assert.assertEquals(expected, serializedMessage)
    }

    /**
     * Serialize a message that has fixed fields but no variable fields
     */
    @Test
    fun serializeMessageFixedFieldsOnly() {
        EasyMock.expect(mockMessageSerializer.messageId)
                .andReturn("97")
        EasyMock.expect(mockMessageSerializer.buildFixedComponents(message))
                .andReturn(listOf(
                        "1",
                        "23",
                        "456"
                ))
        EasyMock.expect(mockMessageSerializer.buildVariableComponents(message))
                .andReturn(emptyList())
        replayAll()

        val expected = "97123456"

        val serializedMessage = testSubject.serialize(message)
        Assert.assertEquals(expected, serializedMessage)
    }

    /**
     * Serialize a message that has variable fields but no fixed fields
     */
    @Test
    fun serializeMessageVariableFieldsOnly() {
        EasyMock.expect(mockMessageSerializer.messageId)
                .andReturn("97")
        EasyMock.expect(mockMessageSerializer.buildFixedComponents(message))
                .andReturn(emptyList())
        EasyMock.expect(mockMessageSerializer.buildVariableComponents(message))
                .andReturn(listOf(
                        "AA" to "Hello",
                        "BB" to "World"
                ))
        replayAll()

        val expected = "97AAHello|BBWorld|"

        val serializedMessage = testSubject.serialize(message)
        Assert.assertEquals(expected, serializedMessage)
    }

    /**
     * Serialize a message that has multiple variable fields with the same key
     */
    @Test
    fun serializeMessageRepeatedVariableFields() {
        EasyMock.expect(mockMessageSerializer.messageId)
                .andReturn("97")
        EasyMock.expect(mockMessageSerializer.buildFixedComponents(message))
                .andReturn(emptyList())
        EasyMock.expect(mockMessageSerializer.buildVariableComponents(message))
                .andReturn(listOf(
                        "AA" to "Hello",
                        "AA" to "World"
                ))
        replayAll()

        val expected = "97AAHello|AAWorld|"

        val serializedMessage = testSubject.serialize(message)
        Assert.assertEquals(expected, serializedMessage)
    }

    /**
     * Serialize a message that has both fixed fields and variable fields
     */
    @Test
    fun serializeMessageFixedVariableFields() {
        EasyMock.expect(mockMessageSerializer.messageId)
                .andReturn("97")
        EasyMock.expect(mockMessageSerializer.buildFixedComponents(message))
                .andReturn(listOf(
                        "1",
                        "23",
                        "456"
                ))
        EasyMock.expect(mockMessageSerializer.buildVariableComponents(message))
                .andReturn(listOf(
                        "AA" to "Hello",
                        "BB" to "World"
                ))
        replayAll()

        val expected = "97123456AAHello|BBWorld|"

        val serializedMessage = testSubject.serialize(message)
        Assert.assertEquals(expected, serializedMessage)
    }
}