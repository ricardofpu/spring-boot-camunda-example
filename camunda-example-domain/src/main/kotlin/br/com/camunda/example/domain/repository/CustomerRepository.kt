package br.com.camunda.example.domain.repository

import br.com.camunda.example.domain.Customer

interface CustomerRepository {

    fun find(id: Customer.Id): Customer?

    fun save(customer: Customer): Int

    fun update(customer: Customer): Int

    fun updateStatus(id: Customer.Id, status: Customer.Status): Int

    fun exists(id: Customer.Id): Boolean

    fun delete(id: Customer.Id): Int

}