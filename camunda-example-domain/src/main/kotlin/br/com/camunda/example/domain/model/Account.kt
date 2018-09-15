package br.com.camunda.example.domain.model

import br.com.camunda.example.domain.entity.DBEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "account")
data class Account(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = "",

    val balanceAmount: Long,

    val balanceScale: Int,

    val balanceCurrency: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    val customer: Customer,

    @OneToMany(mappedBy = "account", targetEntity = Credit::class, fetch = FetchType.LAZY)
    val credits: List<Credit> = listOf(),

    @OneToMany(mappedBy = "account", targetEntity = Debit::class, fetch = FetchType.LAZY)
    val debits: List<Debit> = listOf()

//    @OneToMany(mappedBy = "transference", targetEntity = Transference::class, fetch = FetchType.LAZY)
//    val transfers: List<Transference> = listOf()

) : DBEntity()