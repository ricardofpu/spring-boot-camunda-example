package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.CustomerApi
import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.request.UpdateCustomerRequest
import br.com.camunda.example.api.v1.response.CustomerResponse
import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.web.utils.toModel
import br.com.camunda.example.web.utils.toResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Customer Controller
 */
@RestController
class CustomerController constructor(
    private val customerService: CustomerService
) : CustomerApi {

    /**
     * Create Customer
     */
    override fun create(@RequestBody @Valid request: CreateCustomerRequest): CustomerResponse {
        val customer = request.toModel()
        val result = customerService.save(customer)
        return result.toResponse()
    }

    /**
     * Find Customer
     * @param id Identify an unique to Customer
     */
    override fun find(@PathVariable("id") id: String): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    /**
     * Update Customer
     * @param id Identify an unique to Customer
     */
    override fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateCustomerRequest
    ): CustomerResponse {
        val customer = request.toModel(id)
        val result = customerService.update(customer)
        return result.toResponse()
    }

    /**
     * Delete Customer
     * @param id Identify an unique to Customer
     */
    override fun delete(@PathVariable("id") id: String) {
        customerService.delete(id)
    }
}