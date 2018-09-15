package br.com.camunda.example.domain

import br.com.camunda.example.domain.enums.PaymentType
import br.com.camunda.example.domain.enums.TransactionType
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.model.PaymentTransaction
import java.time.Instant
import java.util.*

fun dummyCustomer() =
    Customer(
        fullName = "Ricardo Borges",
        nickName = "Ricardo",
        email = "ricardoborges@test.com",
        gender = "MALE",
        phoneNumber = "3499998888",
        birthDate = Date(1992, 6, 29)
    )

fun dummyPaymentTransaction(
    id: String = randomUUID(),
    paymentAmount: Int = 1000,
    paymentScale: Int = 2,
    paymentCurrency: String = "BRL",
    transactionId: String = randomUUID(),
    transactionType: TransactionType = TransactionType.PAYMENT,
    type: PaymentType = PaymentType.CREDIT,
    customer: Customer = dummyCustomer()
): PaymentTransaction =
    PaymentTransaction(
        id = id,
        paymentAmount = paymentAmount,
        paymentScale = paymentScale,
        paymentCurrency = paymentCurrency,
        transactionId = transactionId,
        transactionType = transactionType,
        type = type,
        customer = customer
    )