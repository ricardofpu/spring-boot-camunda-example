package br.com.camunda.example.repository

import br.com.camunda.example.domain.dummyDebit
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.repository.config.RepositoryBaseTest
import org.junit.Test
import org.springframework.dao.DataIntegrityViolationException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DebitRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save debit and then find by id`() {
        val account = createAccount()

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
    fun `should save debit and then find by account id`() {
        val account = createAccount()

        val debit = dummyDebit(account = account)

        val savedDebit = debitRepository.save(debit)
        assertNotNull(savedDebit)

        val find = debitRepository.findByAccountId(savedDebit.account.id)
        assertNotNull(find)
        assertTrue(find.first().id == savedDebit.id)
    }

    @Test
    fun `should save debit and then find by transaction id`() {
        val account = createAccount()

        val debit = dummyDebit(account = account)

        val savedDebit = debitRepository.save(debit)
        assertNotNull(savedDebit)

        val find = debitRepository.findByTransactionId(savedDebit.transactionId)
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

    @Test(expected = DataIntegrityViolationException::class)
    fun `shouldn't save debit when transaction id is duplicated`() {
        val account = createAccount()

        val firstDebit = dummyDebit(account = account)

        val savedDebit = debitRepository.save(firstDebit)
        assertNotNull(savedDebit)

        val secondDebit = dummyDebit(account = account, transactionId = firstDebit.transactionId)

        debitRepository.save(secondDebit)
    }

    @Test
    fun `shouldn't find debit when not exists`() {
        val find = debitRepository.findOne(randomUUID())
        assertNull(find)
    }

    @Test
    fun `shouldn't find debit by account id when not exists`() {
        val find = debitRepository.findByAccountId(randomUUID())
        assertTrue(find.isEmpty())
    }

    @Test
    fun `shouldn't find debit by transaction id when not exists`() {
        val find = debitRepository.findByTransactionId(randomUUID())
        assertNull(find)
    }

}