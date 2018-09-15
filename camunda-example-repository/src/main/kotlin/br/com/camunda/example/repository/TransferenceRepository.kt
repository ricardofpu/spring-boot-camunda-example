package br.com.camunda.example.repository

import br.com.camunda.example.domain.model.Transference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferenceRepository : JpaRepository<Transference, String> {

    fun findByOriginAccountId(originAccountId: String): List<Transference>

    fun findByDestinationAccountId(destinationAccountId: String): List<Transference>

    fun findByTransactionId(transactionId: String): Transference

}