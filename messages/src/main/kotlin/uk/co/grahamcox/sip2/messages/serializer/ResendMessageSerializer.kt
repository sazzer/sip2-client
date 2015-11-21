package uk.co.grahamcox.sip2.messages.serializer

import uk.co.grahamcox.sip2.messages.ResendMessage

/**
 * Message Serializer for working with the Resend message
 */
class ResendMessageSerializer : MessageSerializer<ResendMessage> {
    /** The Message ID for the Resend Message */
    override val messageId: String = "97"

    /** There are no fixed components for a Resend message */
    override fun buildFixedComponents(message: ResendMessage): List<String> = emptyList()

    /** There are no variable components for a Resend message */
    override fun buildVariableComponents(message: ResendMessage): List<Pair<String, String>> = emptyList()
}