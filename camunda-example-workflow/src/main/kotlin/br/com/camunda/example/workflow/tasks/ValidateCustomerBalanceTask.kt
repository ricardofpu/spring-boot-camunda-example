package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.enums.PaymentStatus
import br.com.camunda.example.domain.enums.PaymentType
import br.com.camunda.example.domain.model.PaymentTransaction
import br.com.camunda.example.infrastructure.exception.BalanceValidationException
import br.com.camunda.example.repository.PaymentTransactionRepository
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ValidateCustomerBalanceTask constructor(
    private val paymentRepository: PaymentTransactionRepository
) : BaseTask() {

    override fun executeTask(execution: DelegateExecution) {
        val payment = paymentRepository.findByTransactionId(transactionId)

        val transferenceValue = BigDecimal.valueOf(payment.paymentAmount.toLong(), payment.paymentScale)

        val payments = paymentRepository.findByCustomerId(customerId)

        val balance = calculateBalance(payments)

        this.validateBalance(balance, transferenceValue)
    }

    private fun calculateBalance(payments: List<PaymentTransaction>): BigDecimal {
        val totalCredit = payments
            .filter { it.type == PaymentType.CREDIT && it.status == PaymentStatus.PROCESSED }
            .map { BigDecimal.valueOf(it.paymentAmount.toLong(), it.paymentScale) }
            .reduce { acc, bigDecimal -> acc.add(bigDecimal) }

        val totalDebit = payments
            .filter { it.type == PaymentType.DEBIT && it.status == PaymentStatus.PROCESSED }
            .map { BigDecimal.valueOf(it.paymentAmount.toLong(), it.paymentScale) }
            .reduce { acc, bigDecimal -> acc.add(bigDecimal) } ?: BigDecimal.ZERO

        return totalCredit.minus(totalDebit)
    }

    private fun validateBalance(balance: BigDecimal, transferenceValue: BigDecimal) {
        if (balance < BigDecimal.ZERO || (balance.minus(transferenceValue) < BigDecimal.ZERO)) {
            throw BalanceValidationException()
        }
    }
}