package br.com.camunda.example.web.service

import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.model.PaymentTransaction
import br.com.camunda.example.domain.service.PaymentService
import br.com.camunda.example.repository.PaymentTransactionRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class PaymentServiceImpl constructor(
    private val repository: PaymentTransactionRepository
) : PaymentService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun findByTransactionId(transactionId: String): PaymentTransaction {
        val result = repository.findByTransactionId(transactionId)

        log.debug("Payment founded in database with values [{}]", result)
        return result
    }

    override fun save(paymentTransaction: PaymentTransaction): PaymentTransaction {
        val result = repository.save(paymentTransaction)

        log.debug("Payment saved in database with values [{}]", result)
        return result
    }

    override fun update(paymentTransaction: PaymentTransaction): PaymentTransaction {
        log.debug("Payment will be updated in database with values [{}]", paymentTransaction)

        val result = repository.save(paymentTransaction)

        log.debug("Payment updated in database with id {}", paymentTransaction.id)

        return result
    }

    override fun transfer(paymentTransaction: PaymentTransaction): PaymentTransaction {
        log.debug("Received request to transference from customerId {}", paymentTransaction.customer.id)

        val result = repository.save(paymentTransaction)

        log.debug("Transference saved in database with values [{}]", result)
        return result
    }
}