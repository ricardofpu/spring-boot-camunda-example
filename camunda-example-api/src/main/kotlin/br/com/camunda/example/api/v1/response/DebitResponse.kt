package br.com.camunda.example.api.v1.response

/**
 * Debit Response
 */
data class DebitResponse(

    /**
     * Identify an unique to debit
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
     * Price
     */
    val price: Price?

) {

    /**
     * Value
     */
    data class Price(

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