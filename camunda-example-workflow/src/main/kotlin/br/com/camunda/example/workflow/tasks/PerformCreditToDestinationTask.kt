package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.enums.PaymentType
import br.com.camunda.example.domain.model.Credit
import br.com.camunda.example.domain.service.AccountService
import br.com.camunda.example.domain.service.TransferenceService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component
import java.util.*

@Component
class PerformCreditToDestinationTask constructor(
    private val transferenceService: TransferenceService,
    private val accountService: AccountService
) : BaseTask() {

    override fun executeTask(execution: DelegateExecution) {
        val transference = transferenceService.findById(transferenceId)

        val credit = Credit(
            transactionId = UUID.randomUUID().toString(),
            origin = transference.originAccount.id,
            description = "Transference by another account",
            type = PaymentType.TRANSFERENCE_CREDIT,
            account = transference.destinationAccount,
            valueAmount = transference.priceAmount,
            valueScale = transference.priceScale,
            valueCurrency = transference.priceCurrency
        )

        accountService.saveCredit(credit)

    }
}