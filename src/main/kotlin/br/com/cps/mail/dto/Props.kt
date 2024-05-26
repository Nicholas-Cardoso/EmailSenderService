package br.com.cps.mail.dto

import java.util.Properties

data class Props(
    val properties: Properties,
    val username: String,
    val password: String
)