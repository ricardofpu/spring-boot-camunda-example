package br.com.camunda.example.domain.service

import br.com.camunda.example.domain.enums.PaymentStatus
import br.com.camunda.example.domain.model.Transference

interface TransferenceService {

    fun findById(id: String): Transference

    fun save(transference: Transference): Transference

    fun updateStatusAndReason(id: String, status: PaymentStatus, reason: String?)

}