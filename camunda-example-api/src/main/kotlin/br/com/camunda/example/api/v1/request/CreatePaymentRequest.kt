package br.com.camunda.example.api.v1.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * Create Payment Request
 */
data class CreatePaymentRequest(

    /**
     * Payment
     */
    @field:NotNull val payment: Payment?,

    /**
     * Payment type
     */
    @field:Pattern(regexp = "CREDIT|DEBIT") val type: String?
) {

    /**
     * Payment object
     */
    data class Payment(

        /**
         * Amount of payment
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