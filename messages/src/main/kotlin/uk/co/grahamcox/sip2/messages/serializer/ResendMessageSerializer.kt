package uk.co.grahamcox.sip2.messages.serializer

import uk.co.grahamcox.sip2.messages.ResendMessage

/**
 * Message Serializer for working with the Resend message
 */
class ResendMessageSerializer : MessageSerializer<ResendMessage> {
    /** The Message ID for the Resend Message */
    override val messageId: String = "97"

    override fun buildFixedComponents(message: ResendMessage): List<String> = emptyList()
}