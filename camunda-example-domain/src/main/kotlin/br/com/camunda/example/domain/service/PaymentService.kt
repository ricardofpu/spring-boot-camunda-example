package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.model.PaymentTransaction

interface PaymentService {

    fun findByTransactionId(transactionId: String): PaymentTransaction

    fun save(paymentTransaction: PaymentTransaction): PaymentTransaction

    fun update(paymentTransaction: PaymentTransaction): PaymentTransaction

    fun transfer(paymentTransaction: PaymentTransaction): PaymentTransaction

}