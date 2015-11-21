package uk.co.grahamcox.sip2.messages.serializer

import uk.co.grahamcox.sip2.messages.Message

/**
 * Strategy for extracting the details of a SIP2 Message that need serializing
 */
interface MessageSerializer<T : Message> {
    /**
     * Get the ID of the Message that this serializer builds
     */
    val messageId: String

    /**
     * Build the fixed components of the given message
     * @param message The message to build the fixed components for
     * @return the fixed components
     */
    fun buildFixedComponents(message: T) : List<String>
}