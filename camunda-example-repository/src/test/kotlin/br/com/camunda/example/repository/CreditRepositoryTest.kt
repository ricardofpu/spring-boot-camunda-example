package br.com.camunda.example.repository

import br.com.camunda.example.domain.dummyCredit
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.repository.config.RepositoryBaseTest
import org.junit.Test
import org.springframework.dao.DataIntegrityViolationException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CreditRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save credit and then find by id`() {
        val account = creatAccount()

        val credit = dummyCredit(account = account)

        val savedCredit = creditRepository.save(credit)
        assertNotNull(savedCredit)

        val find = creditRepository.findOne(savedCredit.id)
        assertNotNull(find)
        assertEquals(savedCredit.id, find.id)
        assertEquals(credit.transactionId, find.transactionId)
        assertEquals(credit.valueAmount, find.valueAmount)
        assertEquals(credit.valueScale, find.valueScale)
        assertEquals(credit.valueCurrency, find.valueCurrency)
        assertEquals(credit.type, find.type)
        assertNotNull(savedCredit.account)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `should save credit and then find by account id`() {
        val account = creatAccount()

        val credit = dummyCredit(account = account)

        val savedCredit = creditRepository.save(credit)
        assertNotNull(savedCredit)

        val find = creditRepository.findByAccountId(savedCredit.account.id)
        assertNotNull(find)
        assertTrue(find.first().id == savedCredit.id)
    }

    @Test
    fun `should save credit and then find by transaction id`() {
        val account = creatAccount()

        val credit = dummyCredit(account = account)

        val savedCredit = creditRepository.save(credit)
        assertNotNull(savedCredit)

        val find = creditRepository.findByTransactionId(savedCredit.transactionId)
        assertNotNull(find)
        assertEquals(savedCredit.id, find.id)
        assertEquals(credit.transactionId, find.transactionId)
        assertEquals(credit.valueAmount, find.valueAmount)
        assertEquals(credit.valueScale, find.valueScale)
        assertEquals(credit.valueCurrency, find.valueCurrency)
        assertEquals(credit.type, find.type)
        assertNotNull(savedCredit.account)
        assertNotNull(find.createdAt)
    }

    @Test(expected = DataIntegrityViolationException::class)
    fun `shouldn't save credit when transaction id is duplicated`() {
        val account = creatAccount()

        val firstCredit = dummyCredit(account = account)

        val savedCredit = creditRepository.save(firstCredit)
        assertNotNull(savedCredit)

        val secondCredit = dummyCredit(account = account, transactionId = firstCredit.transactionId)

        creditRepository.save(secondCredit)
    }

    @Test
    fun `shouldn't find credit when not exists`() {
        val find = creditRepository.findOne(randomUUID())
        assertNull(find)
    }

    @Test
    fun `shouldn't find credit by account id when not exists`() {
        val find = creditRepository.findByAccountId(randomUUID())
        assertTrue(find.isEmpty())
    }

    @Test
    fun `shouldn't find credit by transaction id when not exists`() {
        val find = creditRepository.findByTransactionId(randomUUID())
        assertNull(find)
    }

}