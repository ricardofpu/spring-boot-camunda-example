package br.com.camunda.example.repository

import br.com.camunda.example.domain.model.Credit
import br.com.camunda.example.domain.model.Debit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository : JpaRepository<Credit, String> {

    fun findByAccountId(accountId: String): List<Credit>

    fun findByTransactionId(transactionId: String): Credit

}