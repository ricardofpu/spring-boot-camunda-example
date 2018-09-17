package br.com.camunda.example.api.v1.request

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * Update Customer Request
 */
data class UpdateCustomerRequest(

    /**
     * Customer full name
     */
    @field:NotNull val fullName: String?,

    /**
     * Customer nick name
     */
    @field:NotNull val nickName: String?,

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
    @field:[NotNull DateTimeFormat(pattern = "yyyy-MM-dd")] val birthDate: Date?

)