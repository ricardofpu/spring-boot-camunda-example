package br.com.camunda.example.web.utils

import br.com.camunda.example.api.v1.response.CustomerResponse
import br.com.camunda.example.api.v1.response.PaymentResponse
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.model.PaymentTransaction

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

fun PaymentTransaction.toResponse(): PaymentResponse =
    PaymentResponse(
        id = this.id,
        payment = PaymentResponse.Payment(
            amount = this.paymentAmount,
            scale = this.paymentScale,
            currency = this.paymentCurrency
        ),
        transactionId = this.transactionId,
        type = this.type.name,
        status = this.status
    )