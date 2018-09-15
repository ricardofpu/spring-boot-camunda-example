package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.enums.PaymentStatus
import br.com.camunda.example.domain.enums.PaymentType
import br.com.camunda.example.domain.enums.TransactionType
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.model.PaymentTransaction
import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.domain.service.PaymentService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
open class CreateTransferenceTask constructor(
    private val customerService: CustomerService,
    private val paymentService: PaymentService
) : BaseTask() {

    @Transactional
    override fun executeTask(execution: DelegateExecution) {
        val customer = customerService.findById(customerId)
        val destination = customerService.findById(destinationCustomerId)
        val payment = paymentService.findByTransactionId(transactionId)

        this.registerOriginDebit(customer, payment)
        this.registerDestinationCredit(destination, payment)

        paymentService.update(payment.copy(status = PaymentStatus.PROCESSED))
    }


    private fun registerOriginDebit(customer: Customer, paymentTransaction: PaymentTransaction) {
        val newTransaction = PaymentTransaction(
            transactionId = UUID.randomUUID().toString(),
            transactionType = TransactionType.TRANSFER,
            status = PaymentStatus.PROCESSED,
            type = PaymentType.DEBIT,
            customer = customer,
            paymentAmount = paymentTransaction.paymentAmount,
            paymentScale = paymentTransaction.paymentScale,
            paymentCurrency = paymentTransaction.paymentCurrency
        )

        paymentService.save(newTransaction)
    }

    private fun registerDestinationCredit(customer: Customer, paymentTransaction: PaymentTransaction) {
        val newTransaction = PaymentTransaction(
            transactionId = UUID.randomUUID().toString(),
            transactionType = TransactionType.PAYMENT,
            status = PaymentStatus.PROCESSED,
            type = PaymentType.CREDIT,
            customer = customer,
            paymentAmount = paymentTransaction.paymentAmount,
            paymentScale = paymentTransaction.paymentScale,
            paymentCurrency = paymentTransaction.paymentCurrency
        )

        paymentService.save(newTransaction)
    }
}