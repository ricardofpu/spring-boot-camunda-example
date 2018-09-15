package br.com.camunda.example.workflow.tasks

import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.domain.service.DebitService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.springframework.stereotype.Component

@Component
open class CreateTransferenceTask constructor(
    private val customerService: CustomerService,
    private val debitService: DebitService
) : BaseTask() {
    override fun executeTask(execution: DelegateExecution) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //    @Transactional
//    override fun executeTask(execution: DelegateExecution) {
//        val customer = customerService.findById(customerId)
//        val destination = customerService.findById(destinationCustomerId)
//        val payment = debitService.findByTransactionId(transactionId)
//
//        this.registerOriginDebit(customer, payment)
//        this.registerDestinationCredit(destination, payment)
//
//        debitService.update(payment.copy(status = PaymentStatus.PROCESSED))
//    }
//
//
//    private fun registerOriginDebit(customer: Customer, paymentTransaction: PaymentTransaction) {
//        val newTransaction = PaymentTransaction(
//            transactionId = UUID.randomUUID().toString(),
//            transactionType = TransactionType.TRANSFER,
//            status = PaymentStatus.PROCESSED,
//            type = PaymentType.DEBIT,
//            customer = customer,
//            paymentAmount = paymentTransaction.paymentAmount,
//            paymentScale = paymentTransaction.paymentScale,
//            paymentCurrency = paymentTransaction.paymentCurrency
//        )
//
//        debitService.save(newTransaction)
//    }
//
//    private fun registerDestinationCredit(customer: Customer, paymentTransaction: PaymentTransaction) {
//        val newTransaction = PaymentTransaction(
//            transactionId = UUID.randomUUID().toString(),
//            transactionType = TransactionType.PAYMENT,
//            status = PaymentStatus.PROCESSED,
//            type = PaymentType.CREDIT,
//            customer = customer,
//            paymentAmount = paymentTransaction.paymentAmount,
//            paymentScale = paymentTransaction.paymentScale,
//            paymentCurrency = paymentTransaction.paymentCurrency
//        )
//
//        debitService.save(newTransaction)
//    }
}