package br.com.camunda.example.api.v1.response

import java.time.LocalDate

/**
 * Customer Response
 */
data class CustomerResponse(

    /**
     * Identify an unique to Customer
     */
    val id: String?,

    /**
     * Full name
     */
    val fullName: String?,

    /**
     * Gender
     */
    val gender: String?,

    /**
     * Phone number
     */
    val phoneNumber: String?,

    /**
     * Email
     */
    val email: String?,

    /**
     * Birth date
     */
    val birthDate: LocalDate?

)