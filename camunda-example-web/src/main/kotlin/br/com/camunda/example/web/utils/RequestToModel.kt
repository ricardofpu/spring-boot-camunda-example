package br.com.camunda.example.web.utils

import br.com.camunda.example.api.v1.request.CreateCustomerRequest
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