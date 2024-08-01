package br.com.cps.mail.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class MailForm(
    val recipientMail: List<String>,
    @field:NotBlank(message = "Subject cannot be null or empty.")
    val subject: String,
    @field:NotBlank(message = "Body cannot be null or empty.")
    val body: String,
    val hasAttachment: Boolean = false
)
