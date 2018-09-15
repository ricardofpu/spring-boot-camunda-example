package br.com.camunda.example.repository

import br.com.camunda.example.domain.dummyAccount
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.repository.config.RepositoryBaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class AccountRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save account and then find by id`() {
        val customer = createCustomer()
        val account = dummyAccount(customer = customer)

        val saved = accountRepository.save(account)
        assertNotNull(saved)

        val find = accountRepository.findOne(saved.id)
        assertNotNull(find)
        assertEquals(saved.id, find.id)
        assertEquals(saved.balanceAmount, find.balanceAmount)
        assertEquals(saved.balanceScale, find.balanceScale)
        assertEquals(saved.balanceCurrency, find.balanceCurrency)
        assertEquals(saved.credits, find.credits)
        assertEquals(saved.customer, find.customer)
        assertEquals(saved.debits, find.debits)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `shouldn't find customer when not exists`() {
        val find = debitRepository.findOne(randomUUID())
        assertNull(find)
    }

//    @Test
//    fun `should find payment by customer id`() {
//        val customer = dummyCustomer()
//
//        val savedCustomer = customerRepository.save(customer)
//        assertNotNull(savedCustomer)
//
//        val payment = dummyPaymentTransaction(customer = savedCustomer)
//
//        val savedPayment = debitRepository.save(payment)
//        assertNotNull(savedPayment)
//
//        val find = debitRepository.findByCustomerId(savedPayment.customer.id)
//        assertNotNull(find)
//        assertTrue(find.isNotEmpty())
//        assertEquals(savedPayment.id, find[0].id)
//        assertEquals(payment.transactionId, find[0].transactionId)
//        assertEquals(payment.paymentAmount, find[0].paymentAmount)
//        assertEquals(payment.paymentScale, find[0].paymentScale)
//        assertEquals(payment.paymentCurrency, find[0].paymentCurrency)
//        assertEquals(payment.type, find[0].type)
//        assertEquals(payment.status, find[0].status)
//        assertNotNull(savedPayment.customer)
//        assertNotNull(find[0].createdAt)
//    }

}