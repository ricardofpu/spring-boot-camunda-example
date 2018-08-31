package br.com.camunda.example.api.v1.request

import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * Create Customer Request
 */
data class CreateCustomerRequest(

    /**
     * Customer full name
     */
    @field:NotNull val fullName: String?,

    /**
     * Customer gender
     */
    @field:Pattern(regexp = "MALE|FEMALE") val gender: String?,

    /**
     * Identify a phone number of customer
     */
    @field:NotNull val phoneNumber: String?,

    /**
     * Email of customer
     */
    val email: String?,

    /**
     * Customer birth date
     */
    @field:NotNull val birthDate: LocalDate?

)