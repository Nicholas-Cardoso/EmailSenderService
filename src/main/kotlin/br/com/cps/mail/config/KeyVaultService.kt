package br.com.cps.mail.config

import com.azure.identity.DefaultAzureCredentialBuilder
import com.azure.security.keyvault.secrets.SecretClientBuilder
import org.springframework.stereotype.Component

@Component
class KeyVaultService(keyVaultUrl: KeyVaultConfig) {
    private val secret = SecretClientBuilder()
        .vaultUrl(keyVaultUrl.keyVault)
        .credential(DefaultAzureCredentialBuilder().build())
        .buildClient()

    fun getSecret(secretName: String): String {
        return secret.getSecret(secretName).value
    }
}