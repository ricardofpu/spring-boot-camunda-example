package br.com.camunda.example.api.v1.response

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Credit Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreditResponse(

    /**
     * Identify an unique to credit
     */
    val id: String?,

    /**
     * Account id
     */
    val accountId: String?,

    /**
     * Transaction id
     */
    val transactionId: String?,

    /**
     * Origin
     */
    val origin: String?,

    /**
     * Description
     */
    val description: String?,

    /**
     * Value of credit
     */
    val value: Value?,

    /**
     * Type
     */
    val type: String?

) {

    /**
     * Value
     */
    data class Value(

        /**
         * Amount
         */
        val amount: Long,

        /**
         * Scale
         */
        val scale: Int,

        /**
         * Currency
         */
        val currency: String
    )
}