package br.com.camunda.example.api.v1.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * Create Transfer Request
 */
data class CreateTransferRequest(

    /**
     * Transference
     */
    @field:NotNull val transferenceValue: Transference?,

    /**
     * Identify an unique to customer that will be receive transference
     */
    @field:NotNull val destinationCustomerId: String?
) {

    /**
     * Transference object
     */
    data class Transference(

        /**
         * Amount of transference
         */
        @field:[NotNull Min(0)] val amount: Int,

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