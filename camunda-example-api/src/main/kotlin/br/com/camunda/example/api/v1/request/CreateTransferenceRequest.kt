package br.com.camunda.example.api.v1.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * Create Transference Request
 */
data class CreateTransferenceRequest(

    /**
     * Identify an unique to account that will receive this transference
     */
    @field:NotNull val destinationAccountId: String,

    /**
     * Identify an unique to this transaction
     */
    @field:NotNull val transactionId: String,

    /**
     * Any description of transference
     */
    val description: String? = null,

    /**
     * Price of transference
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