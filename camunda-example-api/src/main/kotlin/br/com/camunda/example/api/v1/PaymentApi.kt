package br.com.camunda.example.api.v1

import br.com.camunda.example.api.v1.request.CreatePaymentRequest
import br.com.camunda.example.api.v1.request.CreateTransferRequest
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
 * Payment API
 */
@RequestMapping("/v1/payments", produces = [MediaType.APPLICATION_JSON_VALUE])
interface PaymentApi {

    /**
     * Create Payment
     * @param customerId Identify an unique to customer that will receive payment
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/customers/{customerId}")
    fun payment(
        @PathVariable("customerId") customerId: String,
        @RequestBody @Valid request: CreatePaymentRequest
    ): PaymentResponse

    /**
     * Create Transfer
     * @param customerId Identify an unique to customer that will do transference
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping("/transfers/customers/{customerId}")
    fun transference(@PathVariable("customerId") customerId: String,
                     @RequestBody @Valid request: CreateTransferRequest): PaymentResponse

}