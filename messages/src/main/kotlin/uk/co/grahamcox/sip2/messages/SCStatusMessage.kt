package uk.co.grahamcox.sip2.messages

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

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
        @field:Max(999) @field:Min(0) val maxPrintWidth: Int,
        @field:Size(min = 4, max = 4) val protocolVersion: String = "2.00"
) : Message