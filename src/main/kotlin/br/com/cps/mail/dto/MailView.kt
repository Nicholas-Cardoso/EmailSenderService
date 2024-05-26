package br.com.cps.mail.dto

import java.time.LocalDateTime

data class MailView(
    val recipientMail: String,
    val subject: String,
    val createdAt: LocalDateTime
)
