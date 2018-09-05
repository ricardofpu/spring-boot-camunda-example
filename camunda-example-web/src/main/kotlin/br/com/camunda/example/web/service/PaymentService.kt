package br.com.camunda.example.web.service

import br.com.camunda.example.domain.model.PaymentTransaction

interface PaymentService {

    fun save(paymentTransaction: PaymentTransaction): PaymentTransaction

}