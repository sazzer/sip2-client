package uk.co.grahamcox.sip2.messages.serializer

import uk.co.grahamcox.sip2.messages.LoginMessage

/**
 * Message Serializer to handle serialization of a Login Message
 */
class LoginMessageSerializer : MessageSerializer<LoginMessage> {
    /** The key for the User ID field */
    private val USER_ID_KEY = "CN"
    /** The key for the Password field */
    private val PASSWORD_KEY = "CO"
    /** The key for the Location field */
    private val LOCATION_KEY = "CP"
    /** The Message ID of a Login Message */
    override val messageId: String = "93"

    /**
     * Build the Fixed Components of a Login Message. These are the indicators for encrypting the User ID and Password
     * @param message The message to get the values from
     * @return the list of fixed components
     */
    override fun buildFixedComponents(message: LoginMessage): List<String> = listOf(
            message.userIdAlgorithm,
            message.passwordAlgorithm
    )

    /**
     * Build the variable components of a login message. These are the username, password and login location
     * @param message The message to get the values from
     * @return the list of values
     */
    override fun buildVariableComponents(message: LoginMessage): List<Pair<String, String>> {
        val values = mapOf(
                USER_ID_KEY to message.userId,
                PASSWORD_KEY to message.password,
                LOCATION_KEY to message.location
        )
            .toList().filter { v -> v.second != null }
            .map { v -> Pair(v.first, v.second!!) } // This is actually safe because the above stripped out the nulls

        return values
    }
}