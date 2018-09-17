package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.service.AccountService
import br.com.camunda.example.domain.service.TransferenceService
import br.com.camunda.example.infrastructure.exception.BalanceNotReservedException
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component

@Component
open class ReserveOriginBalanceTask constructor(
    private val accountService: AccountService,
    private val transferenceService: TransferenceService
) : BaseTask() {

    companion object {
        const val IS_BALANCE_RESERVED = "isBalanceReserved"
    }

    override fun executeTask(execution: DelegateExecution) {
        val transference = transferenceService.findById(transferenceId)

        try {
            accountService.reserveBalance(transference)
            execution.setVariable(IS_BALANCE_RESERVED, true)
        } catch (e: BalanceNotReservedException) {
            execution.setVariable(IS_BALANCE_RESERVED, false)
        }
    }

}
