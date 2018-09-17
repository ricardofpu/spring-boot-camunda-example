package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.model.Credit

interface CreditService {

    fun findByTransactionId(transactionId: String): Credit

    fun save(credit: Credit): Credit

}