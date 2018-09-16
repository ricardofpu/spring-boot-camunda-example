package br.com.camunda.example.web.utils

import br.com.camunda.example.api.v1.response.AccountResponse
import br.com.camunda.example.api.v1.response.CreditResponse
import br.com.camunda.example.api.v1.response.CustomerResponse
import br.com.camunda.example.domain.model.Account
import br.com.camunda.example.domain.model.Credit
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

fun Account.toResponse(): AccountResponse =
    AccountResponse(
        id = this.id,
        customerId = this.customer.id,
        balance = AccountResponse.Balance(
            amount = this.balanceAmount,
            scale = this.balanceScale,
            currency = this.balanceCurrency
        )
    )

fun Credit.toResponse(): CreditResponse =
    CreditResponse(
        id = this.id,
        transactionId = this.transactionId,
        origin = this.origin,
        description = this.description,
        type = this.type.name,
        accountId = this.account.id,
        value = CreditResponse.Value(
            amount = this.valueAmount,
            scale = this.valueScale,
            currency = this.valueCurrency
        )
    )