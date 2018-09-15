package br.com.camunda.example.repository

import br.com.camunda.example.domain.model.Debit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DebitRepository : JpaRepository<Debit, String> {

    fun findByCustomerId(customerId: String): List<Debit>

    fun findByTransactionId(transactionId: String): Debit

}