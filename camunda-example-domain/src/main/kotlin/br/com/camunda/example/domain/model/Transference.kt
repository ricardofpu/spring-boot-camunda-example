package br.com.camunda.example.domain.model

import br.com.camunda.example.domain.entity.DBEntity
import br.com.camunda.example.domain.enums.PaymentStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "transference")
data class Transference(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = "",

    val transactionId: String,

    val description: String,

    val priceAmount: Long,

    val priceScale: Int,

    val priceCurrency: String,

    @Convert(converter = PaymentStatusConverter::class)
    var status: PaymentStatus = PaymentStatus.PENDING,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_account_id")
    @NotNull
    val originAccount: Account,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    @NotNull
    val destinationAccount: Account

) : DBEntity() {

    var reason: String? = null
    var reversedAt: Date? = null

    companion object {
        class PaymentStatusConverter : AttributeConverter<PaymentStatus, String> {
            override fun convertToDatabaseColumn(paymentStatus: PaymentStatus?): String? {
                return paymentStatus?.name
            }

            override fun convertToEntityAttribute(value: String?): PaymentStatus? {
                return if (value == null) null else Arrays.stream(PaymentStatus.values())
                    .filter { p -> p.name == value }
                    .findFirst()
                    .orElseThrow { IllegalArgumentException() }
            }
        }
    }
}
