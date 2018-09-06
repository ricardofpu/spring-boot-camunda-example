package br.com.camunda.example.api.v1.response

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Payment Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class PaymentResponse(

    /**
     * Identify an unique to Payment
     */
    val id: String?,

    /**
     * Transaction id
     */
    val transactionId: String?,

    /**
     * Transaction type
     */
    val transactionType: String?,

    /**
     * Payment
     */
    val payment: PaymentResponse.Payment?,

    /**
     * Payment Type
     */
    val type: String?,

    /**
     * Status
     */
    val status: String?,

    /**
     * Destination Customer id
     */
    val destinationCustomerId: String?

) {

    /**
     * Payment object
     */
    data class Payment(

        /**
         * Amount
         */
        val amount: Int?,

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