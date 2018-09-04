package br.com.camunda.example.api.v1.response

import java.util.*

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
     * Nick name
     */
    val nickName: String?,

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
    val birthDate: Date?,

    /**
     * Status
     */
    val status: String?

)