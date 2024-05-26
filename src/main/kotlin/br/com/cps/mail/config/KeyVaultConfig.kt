package br.com.cps.mail.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class KeyVaultConfig {
    @Value("\${azure.key.url}")
    lateinit var keyVault: String
}
