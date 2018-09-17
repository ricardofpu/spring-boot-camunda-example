package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.model.Account
import br.com.camunda.example.domain.model.Credit
import br.com.camunda.example.domain.model.Debit
import br.com.camunda.example.domain.model.Transference

interface AccountService {

    fun findById(id: String): Account

    fun save(account: Account): Account

    fun update(account: Account): Account

    fun delete(id: String)

    fun saveCredit(credit: Credit): Credit

    fun saveDebit(debit: Debit): Debit

    fun reserveBalance(transference: Transference)

}