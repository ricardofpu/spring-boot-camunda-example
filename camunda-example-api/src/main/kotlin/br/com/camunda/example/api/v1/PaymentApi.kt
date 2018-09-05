package br.com.camunda.example.api.v1

import br.com.camunda.example.api.v1.request.CreatePaymentRequest
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
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping("/customers/{customerId}")
    fun create(
        @PathVariable("customerId") customerId: String,
        @RequestBody @Valid request: CreatePaymentRequest
    ): PaymentResponse

}