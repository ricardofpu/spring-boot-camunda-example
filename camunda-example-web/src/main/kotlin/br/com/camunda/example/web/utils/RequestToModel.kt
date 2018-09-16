package br.com.camunda.example.web.utils

import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.request.UpdateCustomerRequest
import br.com.camunda.example.domain.model.Customer

fun CreateCustomerRequest.toModel(): Customer =
    Customer(
        fullName = this.fullName!!,
        nickName = this.nickName!!,
        gender = this.gender!!,
        phoneNumber = this.phoneNumber!!,
        email = this.email,
        birthDate = this.birthDate!!
    )

fun UpdateCustomerRequest.toModel(id: String): Customer =
    Customer(
        id = id,
        fullName = this.fullName!!,
        nickName = this.nickName!!,
        gender = this.gender!!,
        phoneNumber = this.phoneNumber!!,
        email = this.email,
        birthDate = this.birthDate!!
    )

//fun CreatePaymentRequest.toModel(customer: Customer): PaymentTransaction =
//    PaymentTransaction(
//        paymentAmount = this.payment?.amount!!,
//        paymentScale = this.payment?.scale!!,
//        paymentCurrency = this.payment?.currency!!,
//        status = PaymentStatus.PROCESSED,
//        type = PaymentType.valueOf(this.type!!),
//        transactionId = UUID.randomUUID().toString(),
//        transactionType = TransactionType.valueOf(this.transactionType!!),
//        customer = customer
//    )
//
//fun CreateTransferRequest.toModel(customer: Customer): PaymentTransaction =
//    PaymentTransaction(
//        paymentAmount = this.transferenceValue?.amount!!,
//        paymentScale = this.transferenceValue?.scale!!,
//        paymentCurrency = this.transferenceValue?.currency!!,
//        type = PaymentType.DEBIT,
//        transactionId = UUID.randomUUID().toString(),
//        transactionType = TransactionType.TRANSFER,
//        customer = customer,
//        destinationCustomerId = this.destinationCustomerId
//    )