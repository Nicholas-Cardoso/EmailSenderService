package br.com.cps.mail.util

import br.com.cps.mail.config.KeyVaultConfig
import br.com.cps.mail.config.KeyVaultService
import br.com.cps.mail.dto.MailForm
import br.com.cps.mail.dto.Props
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

fun MimeMessage.builderMimeMessage(props: Props, mailForm: MailForm): MimeMessage {
    val msg = MimeMessage(session)
    msg.setFrom(InternetAddress(props.username))
    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailForm.recipientMail))
    msg.subject = mailForm.subject
    msg.setText(mailForm.body)

    return MimeMessage(msg)
}

fun senderMail(mail: MailForm, keyVault: KeyVaultConfig) {
    val props = configureMailProps(keyVault)
    val session: Session = Session.getInstance(props.properties,
        object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(props.username, props.password)
            }
        })

    val msg = MimeMessage(session).builderMimeMessage(props, mail)
    Transport.send(msg)
}

fun configureMailProps(keyVault: KeyVaultConfig): Props {
    val props = Properties()
    val keys = KeyVaultService(keyVault)

    props["mail.smtp.auth"] = "true"
    props["mail.smtp.starttls.enable"] = "true"
    props["mail.smtp.host"] = "smtp.gmail.com"
    props["mail.smtp.port"] = "587"

    return Props(
        props,
        keys.getSecret("LoginSecrets"),
        keys.getSecret("LoginSecretsKey")
    )
}