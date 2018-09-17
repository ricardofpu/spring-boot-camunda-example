package br.com.camunda.example.api.v1

import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.request.UpdateCustomerRequest
import br.com.camunda.example.api.v1.response.CustomerResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    /**
     * Find Customer
     * @param id Identify an unique to Customer
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: String): CustomerResponse

    /**
     * Update Customer
     * @param id Identify an unique to Customer
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: UpdateCustomerRequest
    ): CustomerResponse

    /**
     * Delete Customer
     * @param id Identify an unique to Customer
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String)

}