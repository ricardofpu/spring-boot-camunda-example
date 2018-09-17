package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.enums.PaymentStatus
import br.com.camunda.example.domain.service.TransferenceService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component

@Component
class FinishTransferenceTask constructor(
    private val transferenceService: TransferenceService
) : BaseTask() {

    override fun executeTask(execution: DelegateExecution) {

        val transferenceStatus = PaymentStatus.valueOf(status.expressionText)

        transferenceService.updateStatusAndReason(
            id = transferenceId,
            status = transferenceStatus,
            reason = reason.expressionText
        )

    }
}