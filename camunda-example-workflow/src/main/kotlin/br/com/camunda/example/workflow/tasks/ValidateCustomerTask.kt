package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.infrastructure.exception.InvalidCustomerStatusException
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component

@Component
class ValidateCustomerTask constructor(
    private val customerService: CustomerService
) : BaseTask() {

    companion object {
        const val IS_CUSTOMER_ACTIVE = "isCustomerActive"
    }

    override fun executeTask(execution: DelegateExecution) {

        try {
            customerService.validateStatus(customerId)
            execution.setVariable(IS_CUSTOMER_ACTIVE, true)
        } catch (e: InvalidCustomerStatusException) {
            execution.setVariable(IS_CUSTOMER_ACTIVE, false)
        }
    }
}