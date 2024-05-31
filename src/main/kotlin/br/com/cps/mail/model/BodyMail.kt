package br.com.cps.mail.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class BodyMail(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val recipientMail: List<String>,
    val subject: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(null, mutableListOf(), "", LocalDateTime.now())
}
