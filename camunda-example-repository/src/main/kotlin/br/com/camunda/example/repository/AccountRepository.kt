package br.com.camunda.example.repository

import br.com.camunda.example.domain.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface AccountRepository : JpaRepository<Account, String> {

    fun findByCustomerId(customerId: String): Account

    @Transactional
    @Modifying
    @Query("UPDATE Account ac SET ac.balanceAmount = :newBalance WHERE ac.id = :accountId")
    fun updateBalanceAmountByAccountId(
        @Param("accountId") id: String, @Param("newBalance") newBalance: Long
    ): Int

}