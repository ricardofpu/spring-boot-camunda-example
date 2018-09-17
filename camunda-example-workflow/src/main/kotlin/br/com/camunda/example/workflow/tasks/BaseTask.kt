package br.com.camunda.example.workflow.tasks

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.impl.el.FixedValue

abstract class BaseTask : JavaDelegate {

    companion object {
        const val TRANSFERENCE_ID = "transferenceId"
        const val ORIGIN_CUSTOMER_ID = "originCustomerId"
        const val DESTINATION_CUSTOMER_ID = "destinationCustomerId"
        const val TRANSACTION_ID = "transactionId"

        lateinit var transferenceId: String
        lateinit var originCustomerId: String
        lateinit var destinationCustomerId: String
        lateinit var transactionId: String
    }

    lateinit var status: FixedValue
    lateinit var reason: FixedValue

    abstract fun executeTask(execution: DelegateExecution)

    override fun execute(execution: DelegateExecution) {
        loadDefaultVariables(execution)
        executeTask(execution)
    }

    private fun loadDefaultVariables(execution: DelegateExecution) {
        transferenceId = execution.getVariable(TRANSFERENCE_ID).toString()
        originCustomerId = execution.getVariable(ORIGIN_CUSTOMER_ID).toString()
        destinationCustomerId = execution.getVariable(DESTINATION_CUSTOMER_ID).toString()
        transactionId = execution.getVariable(TRANSACTION_ID).toString()
    }
}