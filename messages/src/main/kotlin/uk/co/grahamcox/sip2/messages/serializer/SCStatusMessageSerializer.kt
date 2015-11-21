package uk.co.grahamcox.sip2.messages.serializer

import uk.co.grahamcox.sip2.messages.SCStatusCode
import uk.co.grahamcox.sip2.messages.SCStatusMessage

/**
 * Message Serializer for the SC Status message
 */
class SCStatusMessageSerializer : MessageSerializer<SCStatusMessage> {
    /** The Message ID of the SC Status Message */
    override val messageId: String = "99"

    /**
     * Build the fixed components of the SC Status Message
     */
    override fun buildFixedComponents(message: SCStatusMessage): List<String> {

        return listOf(
                getStatusCode(message.statusCode),
                message.maxPrintWidth.toString(),
                message.protocolVersion
        )
    }

    /**
     * Get the SIP2 Code for the given Status Code
     * @param status The status to look up
     * @return the SIP2 code to use
     */
    fun getStatusCode(status : SCStatusCode) : String = when(status) {
        SCStatusCode.OK -> "0"
        SCStatusCode.OUT_OF_PAPER -> "1"
        SCStatusCode.SHUTDOWN -> "2"
    }
}