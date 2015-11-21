package uk.co.grahamcox.sip2.messages

/**
 * Base interface for SIP2 Message classes
 */
interface Message

/**
 * Annotation for a Message class to indicate the Message ID
 */
annotation class MessageId(val value: String)