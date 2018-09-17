package br.com.camunda.example.workflow.service

import br.com.camunda.example.domain.model.Transference
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
        const val TRANSFERENCE_ID = "transferenceId"
        const val ORIGIN_CUSTOMER_ID = "originCustomerId"
        const val DESTINATION_CUSTOMER_ID = "destinationCustomerId"
    }

    private val log = LogManager.getLogger(this.javaClass)

    override fun start(transference: Transference) {
        val variables = setupVariables(transference)
        runtimeService.startProcessInstanceByKey(
            TRANSFERENCE_WORKFLOW,
            transference.id,
            variables
        )
        log.debug("Transference process started with business key {}", transference.id)
    }

    private fun setupVariables(transference: Transference): MutableMap<String, Any> {
        val variables = mutableMapOf<String, Any>()
        variables.apply {
            put(TRANSFERENCE_ID, transference.id)
            put(ORIGIN_CUSTOMER_ID, transference.originAccount.customer.id)
            put(DESTINATION_CUSTOMER_ID, transference.destinationAccount.customer.id)
        }
        return variables
    }
}