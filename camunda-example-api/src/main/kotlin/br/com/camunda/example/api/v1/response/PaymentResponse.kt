package br.com.camunda.example.api.v1.response

/**
 * Payment Response
 */
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
    val status: String?

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