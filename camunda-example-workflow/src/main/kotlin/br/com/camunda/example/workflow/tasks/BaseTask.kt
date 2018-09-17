package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.workflow.service.WorkflowManager
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.impl.el.FixedValue

abstract class BaseTask : JavaDelegate {

    companion object {
        lateinit var transferenceId: String
        lateinit var originCustomerId: String
        lateinit var destinationCustomerId: String
    }

    lateinit var status: FixedValue
    lateinit var reason: FixedValue

    abstract fun executeTask(execution: DelegateExecution)

    override fun execute(execution: DelegateExecution) {
        loadDefaultVariables(execution)
        executeTask(execution)
    }

    private fun loadDefaultVariables(execution: DelegateExecution) {
        transferenceId = execution.getVariable(WorkflowManager.TRANSFERENCE_ID).toString()
        originCustomerId = execution.getVariable(WorkflowManager.ORIGIN_CUSTOMER_ID).toString()
        destinationCustomerId = execution.getVariable(WorkflowManager.DESTINATION_CUSTOMER_ID).toString()
    }
}