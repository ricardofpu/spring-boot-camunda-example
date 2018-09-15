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
        assertEquals(saved.customer, find.customer)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `should find account by customer id`() {
        val customer = createCustomer()
        val account = dummyAccount(customer = customer)

        val saved = accountRepository.save(account)
        assertNotNull(saved)

        val find = accountRepository.findByCustomerId(saved.customer.id)
        assertNotNull(find)
        assertEquals(saved.id, find.id)
        assertEquals(saved.balanceAmount, find.balanceAmount)
        assertEquals(saved.balanceScale, find.balanceScale)
        assertEquals(saved.balanceCurrency, find.balanceCurrency)
        assertEquals(saved.customer, find.customer)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `shouldn't find account when not exists`() {
        val find = accountRepository.findOne(randomUUID())
        assertNull(find)
    }

}