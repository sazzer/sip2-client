package uk.co.grahamcox.sip2.messages.serializer

import org.slf4j.LoggerFactory
import uk.co.grahamcox.sip2.messages.Message

/**
 * Mechanism by which we can serialize SIP2 Messages into strings
 * @property serializers The map of serializers to use for serializing SIP2 Messages
 */
class Serializer(val serializers: Map<Class<out Message>, MessageSerializer<out Message>>) {
    /** The character that separates variable fields */
    private val VARIABLE_FIELD_SEPARATOR = "|"
    /** The logger to use */
    private val LOG = LoggerFactory.getLogger(Serializer::class.java)
    /**
     * Actually serialize a SIP2 Message into a String. This does not include the Sequence Number or Checksum components
     * as they need to be appended by the transport layer at the appropriate time
     * @param message The message to serialize
     * @return the serialized message
     */
    fun <T : Message> serialize(message: T) : String {
        val messageClass = message.javaClass
        LOG.debug("Serializing message {} of type {}", message, messageClass)
        val serializer = serializers[messageClass] as MessageSerializer<T>?

        if (serializer == null) {
            LOG.error("Attempted to serialize message of unexpected type {}", messageClass)
            throw IllegalArgumentException("No Serializer found for message")
        }

        val messageId: String = serializer.messageId
        val fixedFields = serializer.buildFixedComponents(message)
        val variableFields = serializer.buildVariableComponents(message)

        val result = StringBuilder()
        result.append(messageId)
        fixedFields.forEach {
            f -> result.append(f)
        }
        variableFields.forEach {
            f -> result.append(f.first)
                .append(f.second)
                .append(VARIABLE_FIELD_SEPARATOR)
        }
        val output = result.toString()
        LOG.debug("Successfully serialized message {} into '{}'", message, output)
        return output
    }
}