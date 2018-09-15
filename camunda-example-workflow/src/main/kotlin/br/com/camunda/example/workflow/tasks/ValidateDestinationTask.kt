package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.service.CustomerService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component

@Component
class ValidateDestinationTask constructor(
    private val customerService: CustomerService
) : BaseTask() {

    override fun executeTask(execution: DelegateExecution) {
        customerService.findById(destinationCustomerId)
    }
}