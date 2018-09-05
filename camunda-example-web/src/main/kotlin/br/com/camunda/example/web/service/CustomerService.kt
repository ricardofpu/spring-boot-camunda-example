package br.com.camunda.example.web.service

import br.com.camunda.example.domain.model.Customer

interface CustomerService {

    fun findById(id: String): Customer

    fun save(customer: Customer): Customer

    fun update(customer: Customer): Customer

    fun delete(customerId: String)

}