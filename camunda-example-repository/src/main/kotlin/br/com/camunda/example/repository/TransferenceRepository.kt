package br.com.camunda.example.repository

import br.com.camunda.example.domain.enums.PaymentStatus
import br.com.camunda.example.domain.model.Transference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TransferenceRepository : JpaRepository<Transference, String> {

    fun findByOriginAccountId(originAccountId: String): List<Transference>

    fun findByDestinationAccountId(destinationAccountId: String): List<Transference>

    fun findByTransactionId(transactionId: String): Transference

    @Transactional
    @Modifying
    @Query("UPDATE Transference t SET t.status = :status, t.reason = :reason WHERE t.id = :id")
    fun updateStatusAndReason(
        @Param("id") id: String,
        @Param("status") status: PaymentStatus,
        @Param("reason") reason: String?
    ): Int

}