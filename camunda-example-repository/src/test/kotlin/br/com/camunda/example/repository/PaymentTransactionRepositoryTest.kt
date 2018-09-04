package br.com.camunda.example.repository

import br.com.camunda.example.domain.dummyCustomer
import br.com.camunda.example.domain.dummyPaymentTransaction
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.repository.config.RepositoryBaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PaymentTransactionRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save payment and then find by id`() {
        val customer = dummyCustomer()

        val savedCustomer = customerRepository.save(customer)
        assertNotNull(savedCustomer)

        val payment = dummyPaymentTransaction(customer = savedCustomer)

        val savedPayment = paymentTransactionRepository.save(payment)
        assertNotNull(savedPayment)

        val find = paymentTransactionRepository.findOne(savedPayment.id)
        assertNotNull(find)
        assertEquals(savedPayment.id, find.id)
        assertEquals(payment.transactionId, find.transactionId)
        assertEquals(payment.paymentAmount, find.paymentAmount)
        assertEquals(payment.paymentScale, find.paymentScale)
        assertEquals(payment.paymentCurrency, find.paymentCurrency)
        assertEquals(payment.type, find.type)
        assertEquals(payment.status, find.status)
        assertNotNull(savedPayment.customer)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `shouldn't find customer when not exists`() {
        val find = paymentTransactionRepository.findOne(randomUUID())
        assertNull(find)
    }


}