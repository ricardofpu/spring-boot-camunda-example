package br.com.camunda.example.web.service

import br.com.camunda.example.domain.EXCEPTION_TEST_FAILED_MESSAGE
import br.com.camunda.example.domain.dummyCustomer
import br.com.camunda.example.domain.enums.CustomerStatus
import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.exception.handler.BusinessException
import br.com.camunda.example.exception.handler.NotFoundException
import br.com.camunda.example.infrastructure.exception.CamundaExampleErrorCode
import br.com.camunda.example.infrastructure.exception.InvalidCustomerStatusException
import br.com.camunda.example.web.ControllerBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CustomerServiceTest : ControllerBaseTest() {

    @Autowired
    private lateinit var customerService: CustomerService

    @Test
    fun `should save customer`() {
        val customer = dummyCustomer()

        val saved = customerService.save(customer)
        assertNotNull(saved)
        assertNotNull(saved.id)
        assertEquals(customer.fullName, saved.fullName)
        assertEquals(customer.nickName, saved.nickName)
        assertEquals(customer.gender, saved.gender)
        assertEquals(customer.birthDate, saved.birthDate)
        assertEquals(customer.email, saved.email)
        assertEquals(customer.phoneNumber, saved.phoneNumber)
        assertTrue(saved.status == CustomerStatus.ACTIVE)
        assertNotNull(saved.createdAt)
    }

    @Test
    fun `should find customer by id`() {
        val customer = dummyCustomer()

        val saved = customerService.save(customer)
        assertNotNull(saved)

        val find = customerService.findById(saved.id)
        assertNotNull(find)
        assertEquals(customer.fullName, find.fullName)
        assertEquals(customer.nickName, find.nickName)
        assertEquals(customer.gender, find.gender)
        assertEquals(customer.birthDate, find.birthDate)
        assertEquals(customer.email, find.email)
        assertEquals(customer.phoneNumber, find.phoneNumber)
        assertTrue(find.status == CustomerStatus.ACTIVE)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `should update customer`() {
        val customer = dummyCustomer()

        var saved = customerService.save(customer)
        assertNotNull(saved)

        val updated = saved.copy(fullName = "New FullName", email = "newemail@test.com")

        saved = customerService.update(updated)
        assertNotNull(saved)

        val find = customerService.findById(saved.id)
        assertNotNull(find)
        assertEquals(updated.fullName, find.fullName)
        assertEquals(updated.nickName, find.nickName)
        assertEquals(updated.gender, find.gender)
        assertEquals(updated.birthDate, find.birthDate)
        assertEquals(updated.email, find.email)
        assertEquals(updated.phoneNumber, find.phoneNumber)
        assertTrue(updated.status == CustomerStatus.ACTIVE)
        assertNotNull(find.createdAt)
        assertNotNull(find.updatedAt)
    }

    @Test
    fun `should update status of customer to inactive`() {
        val customer = dummyCustomer()

        var saved = customerService.save(customer)
        assertNotNull(saved)

        val updated = customerService.updateStatus(saved.id, CustomerStatus.INACTIVE)
        assertNotNull(saved)

        val find = customerService.findById(saved.id)
        assertNotNull(find)
        assertEquals(updated.fullName, find.fullName)
        assertEquals(updated.nickName, find.nickName)
        assertEquals(updated.gender, find.gender)
        assertEquals(updated.birthDate, find.birthDate)
        assertEquals(updated.email, find.email)
        assertEquals(updated.phoneNumber, find.phoneNumber)
        assertTrue(updated.status == CustomerStatus.INACTIVE)
        assertNotNull(find.createdAt)
        assertNotNull(find.updatedAt)
    }

    @Test
    fun `shouldn't update status of customer to active when already is active`() {
        val customer = dummyCustomer()

        val saved = customerService.save(customer)
        assertNotNull(saved)

        try {
            customerService.updateStatus(saved.id, CustomerStatus.ACTIVE)
            throw Exception(EXCEPTION_TEST_FAILED_MESSAGE)
        } catch (e: BusinessException) {
            assertTrue(e.errorCode?.code == CamundaExampleErrorCode.CHANGE_STATUS_NOT_ALLOWED.code)
        }
    }

    //TODO(fix test)
    @Test
    fun `shouldn't update status of customer to active when already is inactive`() {
        val customer = dummyCustomer()

        val saved = customerService.save(customer)
        assertNotNull(saved)

        try {
            customerService.updateStatus(saved.id, CustomerStatus.ACTIVE)
            throw Exception(EXCEPTION_TEST_FAILED_MESSAGE)
        } catch (e: BusinessException) {
            assertTrue(e.errorCode?.code == CamundaExampleErrorCode.CHANGE_STATUS_NOT_ALLOWED.code)
        }
    }

    @Test(expected = NotFoundException::class)
    fun `should delete customer`() {
        val customer = dummyCustomer()

        val saved = customerService.save(customer)
        assertNotNull(saved)

        customerService.delete(saved.id)

        customerService.findById(saved.id)
    }

    @Test
    fun `should validate customer`() {
        val customer = dummyCustomer()

        val saved = customerService.save(customer)
        assertNotNull(saved)

        customerService.validateStatus(saved.id)
    }

    @Test(expected = InvalidCustomerStatusException::class)
    fun `shouldn't validate customer when status is inactive`() {
        val customer = dummyCustomer()

        val saved = customerService.save(customer)
        assertNotNull(saved)

        val updated = customerService.updateStatus(saved.id, CustomerStatus.INACTIVE)
        assertNotNull(updated)

        customerService.validateStatus(updated.id)
    }

}