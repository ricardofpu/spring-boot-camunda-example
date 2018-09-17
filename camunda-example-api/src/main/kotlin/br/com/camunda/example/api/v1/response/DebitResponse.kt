package br.com.camunda.example.api.v1.response

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Debit Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class DebitResponse(

    /**
     * Identify an unique to debit
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
     * Price
     */
    val price: Price?,

    /**
     * Type
     */
    val type: String?

) {

    /**
     * Value
     */
    data class Price(

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