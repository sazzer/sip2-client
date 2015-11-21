package uk.co.grahamcox.sip2.messages.serializer

import org.slf4j.LoggerFactory
import uk.co.grahamcox.sip2.messages.Message

/**
 * Mechanism by which we can serialize SIP2 Messages into strings
 * @property serializers The map of serializers to use for serializing SIP2 Messages
 */
class Serializer(val serializers: Map<Class<*>, MessageSerializer<*>>) {
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(Serializer::class.java)
    /**
     * Actually serialize a SIP2 Message into a String. This does not include the Sequence Number or Checksum components
     * as they need to be appended by the transport layer at the appropriate time
     * @param message The message to serialize
     * @return the serialized message
     */
    fun serialize(message: Message) : String {
        val messageClass = message.javaClass
        LOG.debug("Serializing message {} of type {}", message, messageClass)
        val serializer = serializers[messageClass]

        if (serializer == null) {
            LOG.error("Attempted to serialize message of unexpected type {}", messageClass)
            throw IllegalArgumentException("No Serializer found for message")
        }

        val messageId: String = serializer.messageId

        val result = StringBuilder()
        result.append(messageId)

        val output = result.toString()
        LOG.debug("Successfully serialized message {} into '{}'", message, output)
        return output
    }
}