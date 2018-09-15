package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.model.PaymentTransaction

interface Workflow {

    fun start(paymentTransaction: PaymentTransaction)
}