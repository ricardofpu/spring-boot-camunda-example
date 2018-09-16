package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.model.Account

interface AccountService {

    fun findById(id: String): Account

    fun save(account: Account): Account

    fun update(account: Account): Account

    fun delete(id: String)


}