package uk.co.grahamcox.sip2.messages.serializer

import uk.co.grahamcox.sip2.messages.Message
import uk.co.grahamcox.sip2.messages.MessageId

/**
 * Mechanism by which we can serialize SIP2 Messages into strings
 */
class Serializer {
    /**
     * Actually serialize a SIP2 Message into a String. This does not include the Sequence Number or Checksum components
     * as they need to be appended by the transport layer at the appropriate time
     * @param message The message to serialize
     * @return the serialized message
     */
    fun serialize(message: Message) : String {
        val messageClass = message.javaClass
        val messageId: MessageId = messageClass.getAnnotation(MessageId::class.java)
                ?: throw IllegalArgumentException("Provided message has no Message ID")

        val result = StringBuilder()
        result.append(messageId.value)

        return result.toString()
    }
}