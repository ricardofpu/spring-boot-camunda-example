package br.com.camunda.example.repository

import br.com.camunda.example.domain.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, String> {

    fun findByCustomerId(customerId: String): Account

}