package br.com.camunda.example.domain

import br.com.camunda.example.domain.enums.PaymentType
import br.com.camunda.example.domain.model.Account
import br.com.camunda.example.domain.model.Customer
import br.com.camunda.example.domain.model.Debit
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

fun dummyAccount(
    id: String = randomUUID(),
    balanceAmount: Long = 1000,
    balanceScale: Int = 2,
    balanceCurrency: String = "BRL",
    customer: Customer = dummyCustomer()
): Account =
    Account(
        id = id,
        balanceAmount = balanceAmount,
        balanceScale = balanceScale,
        balanceCurrency = balanceCurrency,
        customer = customer
    )

fun dummyDebit(
    id: String = randomUUID(),
    origin: String = "ORIGIN",
    description: String? = "Description",
    priceAmount: Long = 1000,
    priceScale: Int = 2,
    priceCurrency: String = "BRL",
    transactionId: String = randomUUID(),
    type: PaymentType = PaymentType.CREDIT,
    account: Account = dummyAccount()
): Debit =
    Debit(
        id = id,
        priceAmount = priceAmount,
        priceScale = priceScale,
        priceCurrency = priceCurrency,
        transactionId = transactionId,
        description = description,
        origin = origin,
        type = type,
        account = account
    )