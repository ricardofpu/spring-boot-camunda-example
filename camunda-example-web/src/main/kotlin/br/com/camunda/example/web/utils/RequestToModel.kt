package br.com.camunda.example.web.utils

import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.request.CreatePaymentRequest
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.model.PaymentTransaction
import java.util.*

fun CreateCustomerRequest.toModel(): Customer =
    Customer(
        fullName = this.fullName!!,
        nickName = this.nickName!!,
        gender = this.gender!!,
        phoneNumber = this.phoneNumber!!,
        email = this.email,
        birthDate = this.birthDate!!
    )

fun CreatePaymentRequest.toModel(customer: Customer): PaymentTransaction =
    PaymentTransaction(
        paymentAmount = this.payment?.amount!!,
        paymentScale = this.payment?.scale!!,
        paymentCurrency = this.payment?.currency!!,
        status = "PROCESSED",
        type = PaymentTransaction.PaymentType.valueOf(this.type!!),
        transactionId = UUID.randomUUID().toString(),
        customer = customer
    )