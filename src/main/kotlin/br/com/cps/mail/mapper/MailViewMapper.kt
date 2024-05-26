package br.com.cps.mail.mapper

import br.com.cps.mail.dto.MailView
import br.com.cps.mail.model.BodyMail
import org.springframework.stereotype.Component

@Component
class MailViewMapper : Mapper<BodyMail, MailView> {
    override fun map(t: BodyMail): MailView {
        return MailView(
            recipientMail = t.recipientMail,
            subject = t.subject,
            createdAt = t.createdAt
        )
    }
}