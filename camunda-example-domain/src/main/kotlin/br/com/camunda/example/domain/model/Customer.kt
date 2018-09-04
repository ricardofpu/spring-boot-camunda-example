package br.com.camunda.example.domain.model

import br.com.camunda.example.domain.entity.DBEntity
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.Table

@Entity
@Table(name = "customer")
data class Customer(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = "",

    val fullName: String,

    val nickName: String,

    val phoneNumber: String,

    val email: String,

    val birthDate: Date,

    val gender: String,

    @OneToMany(mappedBy = "customer", targetEntity = PaymentTransaction::class, fetch = FetchType.LAZY)
    @OrderBy("id")
    val payments: List<PaymentTransaction> = listOf()

) : DBEntity() {

    @Convert(converter = CustomerStatusConverter::class)
    var status: Status = Status.ACTIVE

    companion object {
        class CustomerStatusConverter : AttributeConverter<Status, String> {
            override fun convertToDatabaseColumn(status: Status?): String? {
                return status?.name
            }

            override fun convertToEntityAttribute(value: String?): Status? {
                return if (value == null) null else Arrays.stream(Status.values())
                    .filter { p -> p.name == value }
                    .findFirst()
                    .orElseThrow { IllegalArgumentException() }
            }
        }
    }

    enum class Status {
        ACTIVE,
        INACTIVE
    }

}