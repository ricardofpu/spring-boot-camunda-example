package br.com.camunda.example.api.v1.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * Create Account Request
 */
data class CreateAccountRequest(

    /**
     * Identify an unique to customer
     */
    @field:NotNull val customerId: String,

    /**
     * Initial Balance
     */
    @field:NotNull val initialBalance: Balance?

) {

    /**
     * Balance
     */
    data class Balance(

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