package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.infrastructure.exception.InvalidCustomerStatusException
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component

@Component
class ValidateOriginTask constructor(
    private val customerService: CustomerService
) : BaseTask() {

    companion object {
        const val IS_ORIGIN_ACTIVE = "isOriginActive"
    }

    override fun executeTask(execution: DelegateExecution) {
        try {
            customerService.validateStatus(originCustomerId)
            execution.setVariable(IS_ORIGIN_ACTIVE, true)
        } catch (e: InvalidCustomerStatusException) {
            execution.setVariable(IS_ORIGIN_ACTIVE, false)
        }
    }
}
