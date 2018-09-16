package br.com.camunda.example.web.utils

import br.com.camunda.example.api.v1.request.CreateAccountRequest
import br.com.camunda.example.api.v1.request.CreateCreditRequest
import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.request.UpdateCustomerRequest
import br.com.camunda.example.domain.model.Account
import br.com.camunda.example.domain.model.Credit
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

fun CreateAccountRequest.toModel(customer: Customer): Account =
    Account(
        balanceAmount = this.initialBalance?.amount!!,
        balanceScale = this.initialBalance?.scale!!,
        balanceCurrency = this.initialBalance?.currency!!,
        customer = customer
    )

fun CreateCreditRequest.toModel(account: Account): Credit =
    Credit(
        transactionId = this.transactionId,
        origin = this.origin,
        description = this.description,
        valueAmount = this.value?.amount!!,
        valueScale = this.value?.scale!!,
        valueCurrency = this.value?.currency!!,
        account = account
    )