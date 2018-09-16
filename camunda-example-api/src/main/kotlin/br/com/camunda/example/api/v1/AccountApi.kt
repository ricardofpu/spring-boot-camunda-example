package br.com.camunda.example.api.v1

import br.com.camunda.example.api.v1.request.CreateAccountRequest
import br.com.camunda.example.api.v1.request.CreateCreditRequest
import br.com.camunda.example.api.v1.request.CreateDebitRequest
import br.com.camunda.example.api.v1.request.CreateTransferRequest
import br.com.camunda.example.api.v1.request.CreateTransferenceRequest
import br.com.camunda.example.api.v1.response.AccountResponse
import br.com.camunda.example.api.v1.response.CreditResponse
import br.com.camunda.example.api.v1.response.DebitResponse
import br.com.camunda.example.api.v1.response.TransferenceResponse
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
     * Perform debit
     * @param id Identify an unique to account that will be debited
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/{id}/debits")
    fun debit(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: CreateDebitRequest
    ): DebitResponse


    /**
     * Perform credit
     * @param id Identify an unique to account that will receive credit
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/{id}/credits")
    fun credit(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: CreateCreditRequest
    ): CreditResponse

    /**
     * Transference
     * @param id Identify an unique to account that will do transference
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/{id}/transfers")
    fun transference(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: CreateTransferenceRequest
    ): TransferenceResponse

}