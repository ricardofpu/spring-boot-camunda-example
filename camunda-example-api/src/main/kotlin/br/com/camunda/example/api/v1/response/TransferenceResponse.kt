package br.com.camunda.example.api.v1.response

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

/**
 * Transference Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransferenceResponse(

    /**
     * Identify an unique to transference
     */
    val id: String?,

    /**
     * Origin account id
     */
    val originAccountId: String?,

    /**
     * Destination account id
     */
    val destinationAccountId: String?,

    /**
     * Transaction id
     */
    val transactionId: String?,

    /**
     * Description
     */
    val description: String?,

    /**
     * Price
     */
    val price: Price?,

    /**
     * Status
     */
    val status: String?,

    /**
     * Reason of reverse
     */
    val reason: String?,

    /**
     * Reversed at
     */
    val reversedAt: Date?

) {

    /**
     * Price
     */
    data class Price(

        /**
         * Amount
         */
        val amount: Long,

        /**
         * Scale that will be applied to amount
         */
        val scale: Int,

        /**
         * Currency
         */
        val currency: String
    )
}