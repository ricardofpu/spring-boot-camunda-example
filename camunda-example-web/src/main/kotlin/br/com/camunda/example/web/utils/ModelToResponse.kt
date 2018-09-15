package br.com.camunda.example.web.utils

import br.com.camunda.example.api.v1.response.CustomerResponse
import br.com.camunda.example.domain.model.Customer

fun Customer.toResponse(): CustomerResponse =
    CustomerResponse(
        id = this.id,
        fullName = this.fullName,
        nickName = this.nickName,
        gender = this.gender,
        phoneNumber = this.phoneNumber,
        email = this.email,
        status = this.status.name,
        birthDate = this.birthDate
    )

//fun PaymentTransaction.toResponse(): PaymentResponse =
//    PaymentResponse(
//        id = this.id,
//        payment = PaymentResponse.Payment(
//            amount = this.paymentAmount,
//            scale = this.paymentScale,
//            currency = this.paymentCurrency
//        ),
//        transactionId = this.transactionId,
//        transactionType = this.transactionType.name,
//        type = this.type.name,
//        status = this.status.name,
//        destinationCustomerId = this.destinationCustomerId
//    )