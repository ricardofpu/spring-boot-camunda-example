package br.com.camunda.example.repository

import br.com.camunda.example.domain.dummyCustomer
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.repository.config.RepositoryBaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class CustomerRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save customer and then find by id`() {
        val customer = dummyCustomer()

        val saved = customerRepository.save(customer)
        assertNotNull(saved)

        val find = customerRepository.findOne(saved.id)
        assertNotNull(find)
        assertEquals(saved.id, find.id)
        assertEquals(customer.fullName, find.fullName)
        assertEquals(customer.nickName, find.nickName)
        assertEquals(customer.email, find.email)
        assertEquals(customer.phoneNumber, find.phoneNumber)
        assertEquals(customer.birthDate, find.birthDate)
        assertEquals(customer.gender, find.gender)
        assertEquals(Customer.Status.ACTIVE, find.status)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `shouldn't find customer when not exists`() {
        val find = customerRepository.findOne(randomUUID())
        assertNull(find)
    }


}