package br.com.cps.mail.controller

import br.com.cps.mail.dto.MailForm
import br.com.cps.mail.service.MailService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/send-mail")
class MailController(
    private val service: MailService
) {
    @GetMapping
    fun getAllMails() = service.getAllListMails()

    @GetMapping("/{id}")
    fun getMailById(@PathVariable id: Long) = service.getMailById(id)

    @PostMapping
    fun createMails(@RequestBody @Valid bodyMail: MailForm) = service.createBodyMails(bodyMail)
}