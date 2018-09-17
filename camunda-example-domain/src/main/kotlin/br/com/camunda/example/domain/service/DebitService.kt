package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.model.Debit

interface DebitService {

    fun findByTransactionId(transactionId: String): Debit

    fun save(debit: Debit): Debit

}