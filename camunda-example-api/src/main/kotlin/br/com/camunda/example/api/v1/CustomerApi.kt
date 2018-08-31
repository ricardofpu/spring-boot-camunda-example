package br.com.camunda.example.api.v1

import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.response.CustomerResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

/**
 * Customer API
 */
@RequestMapping("/v1/customers", produces = [MediaType.APPLICATION_JSON_VALUE])
interface CustomerApi {

    /**
     * Create Customer
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    fun create(@RequestBody @Valid request: CreateCustomerRequest): CustomerResponse

}