package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.AccountApi
import br.com.camunda.example.api.v1.request.CreateAccountRequest
import br.com.camunda.example.api.v1.request.CreateCreditRequest
import br.com.camunda.example.api.v1.request.CreateDebitRequest
import br.com.camunda.example.api.v1.request.CreateTransferenceRequest
import br.com.camunda.example.api.v1.response.AccountResponse
import br.com.camunda.example.api.v1.response.CreditResponse
import br.com.camunda.example.api.v1.response.DebitResponse
import br.com.camunda.example.api.v1.response.TransferenceResponse
import br.com.camunda.example.domain.service.AccountService
import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.web.utils.toModel
import br.com.camunda.example.web.utils.toResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Account Controller
 */
@RestController
class AccountController constructor(
    private val accountService: AccountService,
    private val customerService: CustomerService
) : AccountApi {

    /**
     * Create Account
     */
    override fun create(@RequestBody @Valid request: CreateAccountRequest): AccountResponse {
        val customer = customerService.findById(request.customerId)
        val account = request.toModel(customer)
        val result = accountService.save(account)
        return result.toResponse()
    }

    /**
     * Find Account
     * @param id Identify an unique to account
     */
    override fun find(@PathVariable("id") id: String): AccountResponse {
        val account = accountService.findById(id)
        return account.toResponse()
    }

    /**
     * Perform debit
     * @param id Identify an unique to account that will be debited
     */
    override fun debit(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: CreateDebitRequest
    ): DebitResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Perform credit
     * @param id Identify an unique to account that will receive credit
     */
    override fun credit(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: CreateCreditRequest
    ): CreditResponse {
        val account = accountService.findById(id)
        val credit = request.toModel(account)
        val result = accountService.saveCredit(credit)
        return result.toResponse()
    }

    /**
     * Create Transference
     * @param id Identify an unique to account that will do transference
     */
    override fun transference(
        @PathVariable("id") id: String,
        @RequestBody @Valid request: CreateTransferenceRequest
    ): TransferenceResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}