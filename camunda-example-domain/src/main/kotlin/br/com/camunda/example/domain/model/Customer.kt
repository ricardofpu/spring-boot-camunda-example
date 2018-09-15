package br.com.camunda.example.domain.model

import br.com.camunda.example.domain.entity.DBEntity
import br.com.camunda.example.domain.enums.CustomerStatus
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

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

    val email: String?,

    @Temporal(TemporalType.DATE)
    val birthDate: Date,

    val gender: String

) : DBEntity() {

    @OneToOne(mappedBy = "customer", targetEntity = Account::class, fetch = FetchType.LAZY)
    lateinit var account: Account

    @Convert(converter = CustomerStatusConverter::class)
    var status: CustomerStatus = CustomerStatus.ACTIVE

    companion object {
        class CustomerStatusConverter : AttributeConverter<CustomerStatus, String> {
            override fun convertToDatabaseColumn(status: CustomerStatus?): String? {
                return status?.name
            }

            override fun convertToEntityAttribute(value: String?): CustomerStatus? {
                return if (value == null) null else Arrays.stream(CustomerStatus.values())
                    .filter { p -> p.name == value }
                    .findFirst()
                    .orElseThrow { IllegalArgumentException() }
            }
        }
    }

}