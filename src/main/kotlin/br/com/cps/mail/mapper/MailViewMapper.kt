package br.com.cps.mail.mapper

import br.com.cps.mail.dto.MailView
import br.com.cps.mail.model.BodyMail
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Component

@Component
class MailViewMapper : Mapper<BodyMail, MailView> {
    override fun map(t: BodyMail): MailView {
        return MailView(
            id = t.id,
            recipientMail = t.recipientMail,
            subject = t.subject,
            createdAt = t.createdAt
        )
    }

    fun mapToPage(t: Page<BodyMail>): Page<MailView> {
        val mails = t.content.map {
            MailView(
                id = it.id,
                recipientMail = it.recipientMail,
                subject = it.subject,
                createdAt = it.createdAt
            )
        }

        return PageImpl(mails, t.pageable, t.totalElements)
    }
}