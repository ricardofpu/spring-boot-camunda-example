package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.PaymentApi
import br.com.camunda.example.api.v1.request.CreatePaymentRequest
import br.com.camunda.example.api.v1.request.CreateTransferRequest
import br.com.camunda.example.api.v1.response.PaymentResponse
import br.com.camunda.example.web.service.CustomerService
import br.com.camunda.example.web.service.PaymentService
import br.com.camunda.example.web.utils.toModel
import br.com.camunda.example.web.utils.toResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Payment Controller
 */
@RestController
class PaymentController constructor(
    private val customerService: CustomerService,
    private val paymentService: PaymentService
) : PaymentApi {

    /**
     * Create Payment
     * @param customerId Identify an unique to customer that will receive payment
     */
    override fun payment(
        @PathVariable("customerId") customerId: String,
        @RequestBody @Valid request: CreatePaymentRequest
    ): PaymentResponse {
        val customer = customerService.findById(customerId)
        val payment = paymentService.save(request.toModel(customer))
        return payment.toResponse()
    }

    /**
     * Create Transference
     * @param customerId Identify an unique to customer that will do transference
     */
    override fun transference(
        @PathVariable("customerId") customerId: String,
        @RequestBody @Valid request: CreateTransferRequest
    ): PaymentResponse {
        val customer = customerService.findById(customerId)
        val payment = paymentService.transfer(request.toModel(customer))
        //TODO: start workflow to do transference
        return payment.toResponse()
    }
}