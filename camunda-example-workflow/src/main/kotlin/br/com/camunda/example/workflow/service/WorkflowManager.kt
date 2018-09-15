package br.com.camunda.example.workflow.service

import br.com.camunda.example.domain.model.PaymentTransaction
import br.com.camunda.example.domain.service.Workflow
import org.apache.logging.log4j.LogManager
import org.camunda.bpm.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class WorkflowManager @Autowired constructor(
    private val runtimeService: RuntimeService
) : Workflow {

    companion object {
        const val TRANSFERENCE_WORKFLOW = "TRANSFERENCE_WORKFLOW"
        const val TRANSACTION_ID = "transactionId"
        const val CUSTOMER_ID = "customerId"
        const val DESTINATION_CUSTOMER_ID = "destinationCustomerId"
    }

    private val log = LogManager.getLogger(this.javaClass)

    override fun start(paymentTransaction: PaymentTransaction) {
        val variables = setupVariables(paymentTransaction)
        runtimeService.startProcessInstanceByKey(
            TRANSFERENCE_WORKFLOW,
            paymentTransaction.transactionId,
            variables
        )
        log.debug("Camunda process started with transactionId {}", paymentTransaction.transactionId)
    }

    private fun setupVariables(paymentTransaction: PaymentTransaction): MutableMap<String, Any> {
        val variables = mutableMapOf<String, Any>()
        with(variables) {
            put(TRANSACTION_ID, paymentTransaction.transactionId)
            put(CUSTOMER_ID, paymentTransaction.customer.id)
            paymentTransaction.destinationCustomerId?.let {
                put(DESTINATION_CUSTOMER_ID, paymentTransaction.destinationCustomerId!!)
            }
        }
        return variables
    }
}