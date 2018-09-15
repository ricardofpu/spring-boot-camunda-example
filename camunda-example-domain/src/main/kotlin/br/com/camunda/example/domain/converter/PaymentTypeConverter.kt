package br.com.camunda.example.domain.converter

import br.com.camunda.example.domain.enums.PaymentType
import java.util.*
import javax.persistence.AttributeConverter

open class PaymentTypeConverter : AttributeConverter<PaymentType, String> {
    override fun convertToDatabaseColumn(paymentType: PaymentType): String {
        return paymentType.name
    }

    override fun convertToEntityAttribute(value: String?): PaymentType? {
        return if (value == null) null else Arrays.stream(PaymentType.values())
            .filter { p -> p.name == value }
            .findFirst()
            .orElseThrow { IllegalArgumentException() }
    }
}