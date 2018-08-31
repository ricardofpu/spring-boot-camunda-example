package br.com.camunda.example.infrastructure.tenant

import org.apache.logging.log4j.LogManager


class TenantContext {

    companion object {
        private val logger = LogManager.getLogger(this.javaClass)
        private val currentTenant = ThreadLocal<String>()

        fun setCurrentTenant(tenant: String) {
            logger.debug("Setting tenant to $tenant")
            currentTenant.set(tenant)
        }

        fun getCurrentTenant() = currentTenant.get()

        fun clear() {
            currentTenant.remove()
        }
    }


}