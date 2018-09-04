package br.com.camunda.example.web.service.impl

import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.exception.handler.NotFoundException
import br.com.camunda.example.exception.handler.error.ResourceValue
import br.com.camunda.example.repository.CustomerRepository
import br.com.camunda.example.web.service.CustomerService
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerServiceImpl constructor(
    private val repository: CustomerRepository
) : CustomerService {

    private val log = LogManager.getLogger(this.javaClass)

    override fun create(customer: Customer): Customer {
        val result = repository.save(customer)

        log.debug("Customer saved in database with values [{}]", result)
        return result
    }

    override fun update(customer: Customer): Customer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(customerId: String) {
        val customer = getCustomer(customerId)

        repository.delete(customerId)

        log.debug("Customer delete in database with id {}", customer.id)
    }


    private fun getCustomer(id: String): Customer {
        return Optional.ofNullable(repository.findOne(id))
            .orElseThrow {
                NotFoundException(ResourceValue(Customer::class.java, id))
            }
    }
}