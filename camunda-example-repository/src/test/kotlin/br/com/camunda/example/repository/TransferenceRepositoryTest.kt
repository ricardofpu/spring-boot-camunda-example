package br.com.camunda.example.repository

import br.com.camunda.example.domain.dummyTransference
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.repository.config.RepositoryBaseTest
import org.junit.Test
import org.springframework.dao.DataIntegrityViolationException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TransferenceRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save transference and then find by id`() {
        val originAccount = createAccount()
        val destinationAccount = createAccount()

        val transference = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference = transferenceRepository.save(transference)
        assertNotNull(savedTransference)

        val find = transferenceRepository.findOne(savedTransference.id)
        assertNotNull(find)
        assertEquals(savedTransference.id, find.id)
        assertEquals(transference.transactionId, find.transactionId)
        assertEquals(transference.description, find.description)
        assertEquals(transference.priceAmount, find.priceAmount)
        assertEquals(transference.priceScale, find.priceScale)
        assertEquals(transference.priceCurrency, find.priceCurrency)
        assertEquals(transference.status, find.status)
        assertEquals(transference.reason, find.reason)
        assertEquals(transference.reversedAt, find.reversedAt)
        assertNotNull(savedTransference.originAccount)
        assertNotNull(savedTransference.description)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `should save transference and then find by origin account id`() {
        val originAccount = createAccount()
        val destinationAccount = createAccount()

        val transference = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference = transferenceRepository.save(transference)
        assertNotNull(savedTransference)

        val find = transferenceRepository.findByOriginAccountId(savedTransference.originAccount.id)
        assertNotNull(find)
        assertEquals(savedTransference.id, find[0].id)
        assertEquals(transference.transactionId, find[0].transactionId)
        assertEquals(transference.description, find[0].description)
        assertEquals(transference.priceAmount, find[0].priceAmount)
        assertEquals(transference.priceScale, find[0].priceScale)
        assertEquals(transference.priceCurrency, find[0].priceCurrency)
        assertEquals(transference.status, find[0].status)
        assertEquals(transference.reason, find[0].reason)
        assertEquals(transference.reversedAt, find[0].reversedAt)
        assertNotNull(savedTransference.originAccount)
        assertNotNull(savedTransference.description)
        assertNotNull(find[0].createdAt)
    }

    @Test
    fun `should save transference and then find by destination account id`() {
        val originAccount = createAccount()
        val destinationAccount = createAccount()

        val transference = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference = transferenceRepository.save(transference)
        assertNotNull(savedTransference)

        val find = transferenceRepository.findByDestinationAccountId(savedTransference.destinationAccount.id)
        assertNotNull(find)
        assertEquals(savedTransference.id, find[0].id)
        assertEquals(transference.transactionId, find[0].transactionId)
        assertEquals(transference.description, find[0].description)
        assertEquals(transference.priceAmount, find[0].priceAmount)
        assertEquals(transference.priceScale, find[0].priceScale)
        assertEquals(transference.priceCurrency, find[0].priceCurrency)
        assertEquals(transference.status, find[0].status)
        assertEquals(transference.reason, find[0].reason)
        assertEquals(transference.reversedAt, find[0].reversedAt)
        assertNotNull(savedTransference.originAccount)
        assertNotNull(savedTransference.description)
        assertNotNull(find[0].createdAt)
    }

    @Test
    fun `should save transference and then find by transaction id`() {
        val originAccount = createAccount()
        val destinationAccount = createAccount()

        val transference = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference = transferenceRepository.save(transference)
        assertNotNull(savedTransference)

        val find = transferenceRepository.findByTransactionId(savedTransference.transactionId)
        assertNotNull(find)
        assertEquals(savedTransference.id, find.id)
        assertEquals(transference.transactionId, find.transactionId)
        assertEquals(transference.description, find.description)
        assertEquals(transference.priceAmount, find.priceAmount)
        assertEquals(transference.priceScale, find.priceScale)
        assertEquals(transference.priceCurrency, find.priceCurrency)
        assertEquals(transference.status, find.status)
        assertEquals(transference.reason, find.reason)
        assertEquals(transference.reversedAt, find.reversedAt)
        assertNotNull(savedTransference.originAccount)
        assertNotNull(savedTransference.description)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `should find all transfers by origin account id`() {
        val originAccount = createAccount()
        val destinationAccount = createAccount()

        val transference1 = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference1 = transferenceRepository.save(transference1)
        assertNotNull(savedTransference1)

        val transference2 = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference2 = transferenceRepository.save(transference2)
        assertNotNull(savedTransference2)

        val transfers = transferenceRepository.findByOriginAccountId(originAccount.id)
        assertTrue(transfers.isNotEmpty())

        assertTrue(transfers.all { it.id == savedTransference1.id || it.id == savedTransference2.id })
    }

    @Test
    fun `should find all transfers by destination account id`() {
        val originAccount = createAccount()
        val destinationAccount = createAccount()

        val transference1 = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference1 = transferenceRepository.save(transference1)
        assertNotNull(savedTransference1)

        val transference2 = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference2 = transferenceRepository.save(transference2)
        assertNotNull(savedTransference2)

        val transfers = transferenceRepository.findByDestinationAccountId(destinationAccount.id)
        assertTrue(transfers.isNotEmpty())

        assertTrue(transfers.all { it.id == savedTransference1.id || it.id == savedTransference2.id })
    }


    @Test(expected = DataIntegrityViolationException::class)
    fun `shouldn't save transference when transaction id is duplicated`() {
        val originAccount = createAccount()
        val destinationAccount = createAccount()

        val firstTransference = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount
        )

        val savedTransference = transferenceRepository.save(firstTransference)
        assertNotNull(savedTransference)

        val find = transferenceRepository.findByTransactionId(savedTransference.transactionId)
        assertNotNull(find)

        val secondTransference = dummyTransference(
            originAccount = originAccount,
            destinationAccount = destinationAccount,
            transactionId = firstTransference.transactionId
        )

        transferenceRepository.save(secondTransference)
    }

    @Test
    fun `shouldn't find transference when not exists`() {
        val find = transferenceRepository.findOne(randomUUID())
        assertNull(find)
    }

    @Test
    fun `shouldn't find credit by origin account id when not exists`() {
        val find = transferenceRepository.findByOriginAccountId(randomUUID())
        assertTrue(find.isEmpty())
    }

    @Test
    fun `shouldn't find credit by destination account id when not exists`() {
        val find = transferenceRepository.findByDestinationAccountId(randomUUID())
        assertTrue(find.isEmpty())
    }

    @Test
    fun `shouldn't find credit by transaction id when not exists`() {
        val find = transferenceRepository.findByTransactionId(randomUUID())
        assertNull(find)
    }

}