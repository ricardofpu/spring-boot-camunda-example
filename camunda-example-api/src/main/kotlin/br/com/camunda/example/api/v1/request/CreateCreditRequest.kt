package br.com.camunda.example.api.v1.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * Create Credit Request
 */
data class CreateCreditRequest(

    /**
     * Identify an unique to this transaction
     */
    @field:NotNull val transactionId: String,

    /**
     * Origin of credit
     */
    @field:NotNull val origin: String,

    /**
     * Any description of credit
     */
    val description: String? = null,

    /**
     * Value of credit
     */
    @field:NotNull val value: Value?

) {

    /**
     * Value
     */
    data class Value(

        /**
         * Amount
         */
        @field:[NotNull Min(0)] val amount: Long,

        /**
         * Scale that will be applied to amount
         */
        @field:[NotNull Min(0)] val scale: Int,

        /**
         * Currency
         */
        @field:NotNull val currency: String
    )
}