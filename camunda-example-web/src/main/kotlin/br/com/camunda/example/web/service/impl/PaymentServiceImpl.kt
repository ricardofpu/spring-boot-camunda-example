package br.com.camunda.example.web.service.impl

import br.com.camunda.example.domain.model.PaymentTransaction
import br.com.camunda.example.repository.PaymentTransactionRepository
import br.com.camunda.example.web.service.PaymentService
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service

@Service
class PaymentServiceImpl constructor(
    private val repository: PaymentTransactionRepository
) : PaymentService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun save(paymentTransaction: PaymentTransaction): PaymentTransaction {
        val result = repository.save(paymentTransaction)

        log.debug("Payment saved in database with values [{}]", result)
        return result
    }

    override fun transfer(paymentTransaction: PaymentTransaction): PaymentTransaction {
        log.debug("Received request to transference from customerId {}", paymentTransaction.customer.id)

        val result = repository.save(paymentTransaction)

        log.debug("Transference saved in database with values [{}]", result)
        return result
    }
}