package br.com.camunda.example.repository

import br.com.camunda.example.domain.dummyAccount
import br.com.camunda.example.domain.dummyCustomer
import br.com.camunda.example.domain.dummyDebit
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.repository.config.RepositoryBaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DebitRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save debit and then find by id`() {
        val customer = dummyCustomer()
        val account = dummyAccount(customer = customer)

        val savedCustomer = customerRepository.save(customer)
        assertNotNull(savedCustomer)

        val debit = dummyDebit(account = account)

        val savedDebit = debitRepository.save(debit)
        assertNotNull(savedDebit)

        val find = debitRepository.findOne(savedDebit.id)
        assertNotNull(find)
        assertEquals(savedDebit.id, find.id)
        assertEquals(debit.transactionId, find.transactionId)
        assertEquals(debit.priceAmount, find.priceAmount)
        assertEquals(debit.priceScale, find.priceScale)
        assertEquals(debit.priceCurrency, find.priceCurrency)
        assertEquals(debit.type, find.type)
        assertNotNull(savedDebit.account)
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