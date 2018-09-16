package br.com.camunda.example.api.v1.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * Create Debit Request
 */
data class CreateDebitRequest(

    /**
     * Identify an unique to this transaction
     */
    @field:NotNull val transactionId: String,

    /**
     * Origin of debit
     */
    @field:NotNull val origin: String,

    /**
     * Any description of debit
     */
    val description: String? = null,

    /**
     * Price of debit
     */
    @field:NotNull val price: Price?

) {

    /**
     * Price
     */
    data class Price(

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