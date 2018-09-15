package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.PaymentApi
import br.com.camunda.example.api.v1.request.CreatePaymentRequest
import br.com.camunda.example.api.v1.request.CreateTransferRequest
import br.com.camunda.example.api.v1.response.PaymentResponse
import br.com.camunda.example.domain.service.CustomerService
import br.com.camunda.example.domain.service.DebitService
import br.com.camunda.example.domain.service.Workflow
import org.springframework.web.bind.annotation.RestController

/**
 * Payment Controller
 */
@RestController
class PaymentController constructor(
    private val customerService: CustomerService,
    private val debitService: DebitService,
    private val workflow: Workflow
) : PaymentApi {
    override fun payment(customerId: String, request: CreatePaymentRequest): PaymentResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transference(customerId: String, request: CreateTransferRequest): PaymentResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //    /**
//     * Create Payment
//     * @param customerId Identify an unique to customer that will receive payment
//     */
//    override fun payment(
//        @PathVariable("customerId") customerId: String,
//        @RequestBody @Valid request: CreatePaymentRequest
//    ): PaymentResponse {
//        val customer = customerService.findById(customerId)
//        val payment = debitService.save(request.toModel(customer))
//        return payment.toResponse()
//    }
//
//    /**
//     * Create Transference
//     * @param customerId Identify an unique to customer that will do transference
//     */
//    override fun transference(
//        @PathVariable("customerId") customerId: String,
//        @RequestBody @Valid request: CreateTransferRequest
//    ): PaymentResponse {
//        val customer = customerService.findById(customerId)
//        val payment = debitService.transfer(request.toModel(customer))
//        workflow.start(payment)
//        return payment.toResponse()
//    }
}