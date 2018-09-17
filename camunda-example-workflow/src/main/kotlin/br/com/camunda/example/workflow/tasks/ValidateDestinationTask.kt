package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.infrastructure.exception.InvalidCustomerStatusException
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component

@Component
class ValidateDestinationTask constructor(
    private val customerService: CustomerService
) : BaseTask() {

    companion object {
        const val IS_DESTINATION_ACTIVE = "isDestinationActive"
    }

    override fun executeTask(execution: DelegateExecution) {
        try {
            customerService.validateStatus(destinationCustomerId)
            execution.setVariable(IS_DESTINATION_ACTIVE, true)
        } catch (e: InvalidCustomerStatusException) {
            execution.setVariable(IS_DESTINATION_ACTIVE, false)
        }
    }
}