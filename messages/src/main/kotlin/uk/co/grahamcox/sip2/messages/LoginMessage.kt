package uk.co.grahamcox.sip2.messages

import javax.validation.constraints.Size

/**
 * Representation of the SIP2 Login Message
 * @property userIdAlgorithm The indicator of the algorithm to use to encrypt the User ID
 * @property passwordAlgorithm The indicator of the algorithm to use to encrypt the Password
 * @property userId The actual User ID to log in as
 * @property password The actual Password to log in with
 * @property location The location to log in at
 */
class LoginMessage(@field:Size(min = 1,  max = 1) val userIdAlgorithm: String = "0",
                   @field:Size(min = 1,  max = 1) val passwordAlgorithm: String = "0",
                   val userId: String?,
                   val password: String?,
                   val location: String?) : Message {

}