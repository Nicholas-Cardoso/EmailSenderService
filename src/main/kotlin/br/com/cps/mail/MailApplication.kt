package br.com.cps.mail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MailApplication

fun main(args: Array<String>) {
	runApplication<MailApplication>(*args)
}
