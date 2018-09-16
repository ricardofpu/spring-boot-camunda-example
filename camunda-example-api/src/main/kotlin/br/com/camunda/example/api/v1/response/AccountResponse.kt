package br.com.camunda.example.api.v1.response

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Account Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AccountResponse(

    /**
     * Identify an unique to Payment
     */
    val id: String?,

    /**
     * Balance
     */
    val balance: Balance?,

    /**
     * Customer id
     */
    val customerId: String?

) {

    /**
     * Balance
     */
    data class Balance(

        /**
         * Amount
         */
        val amount: Long?,

        /**
         * Scale
         */
        val scale: Int?,

        /**
         * Currency
         */
        val currency: String?
    )
}