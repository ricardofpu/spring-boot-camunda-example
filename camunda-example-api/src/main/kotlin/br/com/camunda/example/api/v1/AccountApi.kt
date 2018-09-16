package br.com.camunda.example.api.v1

import br.com.camunda.example.api.v1.request.CreateAccountRequest
import br.com.camunda.example.api.v1.request.CreateTransferRequest
import br.com.camunda.example.api.v1.response.AccountResponse
import br.com.camunda.example.api.v1.response.PaymentResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

/**
 * Account API
 */
@RequestMapping("/v1/accounts", produces = [MediaType.APPLICATION_JSON_VALUE])
interface AccountApi {

    /**
     * Create Account
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    fun create(@RequestBody @Valid request: CreateAccountRequest): AccountResponse

    /**
     * Create Account
     * @param id Identify an unique to account that will receive debit
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/{id}/debits")
    fun debit(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: CreateAccountRequest
    ): AccountResponse

    /**
     * Create Transfer
     * @param customerId Identify an unique to customer that will do transference
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping("/transfers/customers/{customerId}")
    fun transference(
        @PathVariable("customerId") customerId: String,
        @RequestBody @Valid request: CreateTransferRequest
    ): PaymentResponse

}