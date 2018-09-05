package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.response.PaymentResponse
import br.com.camunda.example.infrastructure.jsonToObject
import br.com.camunda.example.infrastructure.objectToJson
import br.com.camunda.example.web.ControllerBaseTest
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PaymentControllerTest : ControllerBaseTest() {

    @Test
    fun `should create payment`() {
        val customer = requestToCreateCustomer()

        val request = buildCreatePaymentRequest()

        this.mockMvc.perform(
            post(
                "/v1/payments/customers/{customerId}", customer.id
            )
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", notNullValue()))
            .andDo {
                val response = it.response.contentAsString.jsonToObject(PaymentResponse::class.java)
                assertNotNull(response)
                assertEquals(request.payment?.amount, response.payment?.amount)
                assertEquals(request.payment?.scale, response.payment?.scale)
                assertEquals(request.payment?.currency, response.payment?.currency)
                assertEquals(request.type, response.type)
                assertNotNull(response.transactionId)
                assertNotNull(response.status)
            }
    }
}