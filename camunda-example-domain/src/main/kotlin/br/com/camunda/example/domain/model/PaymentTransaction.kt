package br.com.camunda.example.domain.model

import br.com.camunda.example.domain.entity.DBEntity
import br.com.camunda.example.domain.enums.PaymentStatus
import br.com.camunda.example.domain.enums.PaymentType
import br.com.camunda.example.domain.enums.TransactionType
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
@Table(name = "payment_transaction")
data class PaymentTransaction(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = "",

    val transactionId: String,

    @Convert(converter = TransactionTypeConverter::class)
    val transactionType: TransactionType,

    val paymentAmount: Int,

    val paymentScale: Int,

    val paymentCurrency: String,

    @Convert(converter = PaymentTypeConverter::class)
    val type: PaymentType,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    val customer: Customer,

    val destinationCustomerId: String? = null

) : DBEntity() {

    @Convert(converter = PaymentStatusConverter::class)
    var status: PaymentStatus = PaymentStatus.PROCESSED

    companion object {
        class PaymentTypeConverter : AttributeConverter<PaymentType, String> {
            override fun convertToDatabaseColumn(paymentType: PaymentType?): String? {
                return paymentType?.name
            }

            override fun convertToEntityAttribute(value: String?): PaymentType? {
                return if (value == null) null else Arrays.stream(PaymentType.values())
                    .filter { p -> p.name == value }
                    .findFirst()
                    .orElseThrow { IllegalArgumentException() }
            }
        }

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

        class TransactionTypeConverter : AttributeConverter<TransactionType, String> {
            override fun convertToDatabaseColumn(transactionType: TransactionType?): String? {
                return transactionType?.name
            }

            override fun convertToEntityAttribute(value: String?): TransactionType? {
                return if (value == null) null else Arrays.stream(TransactionType.values())
                    .filter { p -> p.name == value }
                    .findFirst()
                    .orElseThrow { IllegalArgumentException() }
            }
        }
    }
}
