package br.com.camunda.example.web.service

import br.com.camunda.example.domain.enums.CustomerStatus
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.exception.handler.BusinessException
import br.com.camunda.example.exception.handler.NotFoundException
import br.com.camunda.example.exception.handler.error.ResourceValue
import br.com.camunda.example.infrastructure.exception.CamundaExampleErrorCode
import br.com.camunda.example.infrastructure.exception.InvalidCustomerStatusException
import br.com.camunda.example.repository.CustomerRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerServiceImpl constructor(
    private val repository: CustomerRepository
) : CustomerService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun findById(id: String): Customer {
        return getCustomer(id)
    }

    override fun save(customer: Customer): Customer {
        val result = repository.save(customer)

        log.debug("Customer saved in database with values [{}]", result)
        return result
    }

    override fun update(customer: Customer): Customer {
        log.debug("Customer will be updated in database with values [{}]", customer)

        val oldCustomer = getCustomer(customer.id)

        customer.status = oldCustomer.status
        customer.createdAt = oldCustomer.createdAt

        val result = repository.save(customer)

        log.debug("Customer updated in database with id {}", customer.id)

        return result
    }

    override fun updateStatus(customerId: String, status: CustomerStatus): Customer {
        log.debug("Status of Customer will be updated in database with id [{}]", customerId)

        val customer = getCustomer(customerId)

        this.validateStatusChange(customer.status, status)

        customer.status = status

        val result = repository.save(customer)

        log.debug("Customer updated in database with id {}", customerId)

        return result
    }

    override fun delete(customerId: String) {
        val customer = getCustomer(customerId)

        repository.delete(customerId)

        log.debug("Customer delete in database with id {}", customer.id)
    }

    override fun validateStatus(customerId: String) {
        val customer = getCustomer(customerId)

        this.validateStatus(customer)

        log.debug("Customer validate status with id {}", customer.id)
    }

    private fun getCustomer(id: String): Customer {
        return Optional.ofNullable(repository.findOne(id))
            .orElseThrow {
                NotFoundException(ResourceValue(Customer::class.java, id))
            }
    }

    private fun validateStatus(customer: Customer) {
        if (customer.status != CustomerStatus.ACTIVE) {
            throw InvalidCustomerStatusException()
        }
    }

    private fun validateStatusChange(oldStatus: CustomerStatus, newStatus: CustomerStatus) {
        when (newStatus) {
            CustomerStatus.ACTIVE -> {
                if (oldStatus != CustomerStatus.INACTIVE) {
                    throw BusinessException(CamundaExampleErrorCode.CHANGE_STATUS_NOT_ALLOWED)
                }
            }
            CustomerStatus.INACTIVE -> {
                if (oldStatus != CustomerStatus.ACTIVE) {
                    throw BusinessException(CamundaExampleErrorCode.CHANGE_STATUS_NOT_ALLOWED)
                }
            }
        }
    }
}