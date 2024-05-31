package br.com.cps.mail.util

import br.com.cps.mail.config.KeyVaultConfig
import br.com.cps.mail.config.KeyVaultService
import br.com.cps.mail.dto.MailForm
import br.com.cps.mail.dto.Props
import java.util.*
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

fun MimeMessage.builderMimeMessage(props: Props, mailForm: MailForm, listMails: String): MimeMessage {
    return MimeMessage(session).apply {
        setFrom(InternetAddress(props.username))
        setRecipients(Message.RecipientType.TO, InternetAddress.parse(listMails))
        subject = mailForm.subject

        val messageBodyPart = MimeBodyPart().apply {
            setText(mailForm.body)
        }
        val multiPart = MimeMultipart().apply {
            addBodyPart(messageBodyPart)
        }
        if (mailForm.hasAttachment) {
            multiPart.addBodyPart(
                MimeBodyPart().apply {
                    val source =
                        FileDataSource("path")
                    dataHandler = DataHandler(source)
                    fileName = source.name
                }
            )
        }

        setContent(multiPart)
    }
}

fun senderMail(mail: MailForm, keyVault: KeyVaultConfig) {
    val props = configureMailProps(keyVault)
    val session: Session = Session.getInstance(props.properties,
        object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(props.username, props.password)
            }
        })

    for (listMails in mail.recipientMail) {
        val messagesToSend = MimeMessage(session).builderMimeMessage(props, mail, listMails)
        Transport.send(messagesToSend)
    }
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