package br.com.camunda.example.workflow.tasks

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

abstract class BaseTask : JavaDelegate {

    companion object {
        const val CUSTOMER_ID = "customerId"
        const val DESTINATION_CUSTOMER_ID = "destinationCustomerId"
        const val TRANSACTION_ID = "transactionId"

        lateinit var customerId: String
        lateinit var destinationCustomerId: String
        lateinit var transactionId: String
    }

    abstract fun executeTask(execution: DelegateExecution)

    override fun execute(execution: DelegateExecution) {
        loadDefaultVariables(execution)
        executeTask(execution)
    }

    private fun loadDefaultVariables(execution: DelegateExecution) {
        customerId = execution.getVariable(CUSTOMER_ID).toString()
        destinationCustomerId = execution.getVariable(DESTINATION_CUSTOMER_ID).toString()
        transactionId = execution.getVariable(TRANSACTION_ID).toString()
    }
}