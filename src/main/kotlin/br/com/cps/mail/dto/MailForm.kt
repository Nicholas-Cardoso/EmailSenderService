package br.com.cps.mail.dto

import jakarta.validation.constraints.NotBlank

data class MailForm(
    @field:NotBlank
    val recipientMail: String,
    @field:NotBlank
    val subject: String,
    @field:NotBlank
    val body: String
)
