package br.com.cps.mail.controller

import br.com.cps.mail.dto.MailForm
import br.com.cps.mail.dto.MailView
import br.com.cps.mail.service.MailService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/send-mail")
class MailController(
    private val service: MailService
) {
    @GetMapping
    fun getAllMails(page: Pageable): Page<MailView> = service.getAllListMails(page)

    @GetMapping("/{id}")
    fun getMailById(@PathVariable id: Long): MailView = service.getMailById(id)

    @PostMapping
    fun createMails(@RequestBody @Valid bodyMail: MailForm): MailView = service.createBodyMails(bodyMail)
}