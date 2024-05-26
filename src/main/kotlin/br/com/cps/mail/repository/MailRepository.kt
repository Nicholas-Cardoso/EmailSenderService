package br.com.cps.mail.repository

import br.com.cps.mail.model.BodyMail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MailRepository : JpaRepository<BodyMail, Long> {

}
