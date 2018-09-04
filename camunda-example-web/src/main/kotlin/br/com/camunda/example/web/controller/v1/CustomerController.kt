package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.CustomerApi
import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.response.CustomerResponse
import br.com.camunda.example.web.service.CustomerService
import br.com.camunda.example.web.utils.toModel
import br.com.camunda.example.web.utils.toResponse
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
        val result = customerService.create(customer)
        return result.toResponse()
    }
}