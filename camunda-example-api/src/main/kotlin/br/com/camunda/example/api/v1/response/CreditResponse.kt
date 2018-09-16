package br.com.camunda.example.api.v1.response

/**
 * Credit Response
 */
data class CreditResponse(

    /**
     * Identify an unique to credit
     */
    val id: String?,

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
     * Value of credit
     */
    val value: Value?

) {

    /**
     * Value
     */
    data class Value(

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