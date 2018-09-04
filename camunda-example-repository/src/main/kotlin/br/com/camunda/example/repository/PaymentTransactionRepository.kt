package br.com.camunda.example.repository

import br.com.camunda.example.domain.model.PaymentTransaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentTransactionRepository : JpaRepository<PaymentTransaction, String>