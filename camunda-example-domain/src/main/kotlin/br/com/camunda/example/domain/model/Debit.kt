package br.com.camunda.example.domain.model

import br.com.camunda.example.domain.converter.PaymentTypeConverter
import br.com.camunda.example.domain.entity.DBEntity
import br.com.camunda.example.domain.enums.PaymentType
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
@Table(name = "debit")
data class Debit(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = "",

    val transactionId: String,

    val origin: String,

    val description: String? = null,

    val priceAmount: Long,

    val priceScale: Int,

    val priceCurrency: String,

    @Convert(converter = PaymentTypeConverter::class)
    val type: PaymentType,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @NotNull
    val account: Account

) : DBEntity()
