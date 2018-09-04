package br.com.camunda.example.domain.model

import br.com.camunda.example.domain.entity.DBEntity
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

    val paymentAmount: Int,

    val paymentScale: Int,

    val paymentCurrency: String,

    @Convert(converter = PaymentTypeConverter::class)
    val type: PaymentType,

    val status: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    val customer: Customer
) : DBEntity() {
    enum class PaymentType {
        DEBIT,
        CREDIT
    }

    companion object {
        class PaymentTypeConverter : AttributeConverter<PaymentType, String> {
            override fun convertToDatabaseColumn(status: PaymentType?): String? {
                return status?.name
            }

            override fun convertToEntityAttribute(value: String?): PaymentType? {
                return if (value == null) null else Arrays.stream(PaymentType.values())
                    .filter { p -> p.name == value }
                    .findFirst()
                    .orElseThrow { IllegalArgumentException() }
            }
        }
    }
}
