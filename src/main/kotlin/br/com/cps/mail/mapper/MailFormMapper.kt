package br.com.cps.mail.mapper

import br.com.cps.mail.dto.MailForm
import br.com.cps.mail.model.BodyMail
import org.springframework.stereotype.Component

@Component
class MailFormMapper : Mapper<MailForm, BodyMail> {
    override fun map(t: MailForm): BodyMail {
        return BodyMail(
            recipientMail = t.recipientMail,
            subject = t.subject
        )
    }
}