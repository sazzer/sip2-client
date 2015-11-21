package uk.co.grahamcox.sip2.messages

/**
 * Enumeration of the supported SC Status Codes
 */
enum class SCStatusCode {
    /** The SIP2 Client is OK */
    OK,
    /** The SIP2 Client is out of paper */
    OUT_OF_PAPER,
    /** The SIP2 Client is about to shut down */
    SHUTDOWN
}

/**
 * Representation of the SIP2 SC Status Message
 * @property statusCode The status of the SIP2 Client
 * @property maxPrintWidth The maximim print width for printing messages
 * @property protocolVersion The protocol version to support
 */
data class SCStatusMessage(
        val statusCode : SCStatusCode,
        val maxPrintWidth: Int,
        val protocolVersion: String = "2.00"
) : Message