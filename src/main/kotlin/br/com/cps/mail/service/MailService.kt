package br.com.cps.mail.service

import br.com.cps.mail.config.KeyVaultConfig
import br.com.cps.mail.dto.MailForm
import br.com.cps.mail.dto.MailView
import br.com.cps.mail.exception.NotFoundException
import br.com.cps.mail.mapper.MailFormMapper
import br.com.cps.mail.mapper.MailViewMapper
import br.com.cps.mail.repository.MailRepository
import br.com.cps.mail.util.senderMail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

private const val notFound = "Mail not found."

@Service
class MailService(
    private val repository: MailRepository,
    private val toView: MailViewMapper,
    private val toForm: MailFormMapper,
    private val keyVault: KeyVaultConfig
) {
    fun getAllListMails(page: Pageable): Page<MailView> {
        return toView.mapToPage(repository.findAll(page))
    }

    fun getMailById(id: Long): MailView {
        val mail = findMailById(id)
        return toView.map(mail)
    }

    fun createBodyMails(bodyMail: MailForm): MailView {
        senderMail(bodyMail, keyVault)
        val mailToBody = toForm.map(bodyMail)
        repository.save(mailToBody)
        return toView.map(mailToBody)
    }

    private fun findMailById(id: Long) =
        repository.findById(id)
            .orElseThrow {
                NotFoundException(notFound)
            }
}
