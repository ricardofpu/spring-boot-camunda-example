package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.response.AccountResponse
import br.com.camunda.example.api.v1.response.CreditResponse
import br.com.camunda.example.domain.enums.PaymentType
import br.com.camunda.example.infrastructure.jsonToObject
import br.com.camunda.example.infrastructure.objectToJson
import br.com.camunda.example.web.ControllerBaseTest
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AccountControllerTest : ControllerBaseTest() {

    @Test
    fun `should create account`() {
        val customer = requestToCreateCustomer()

        val request = buildCreateAccountRequest(customerId = customer.id!!)

        this.mockMvc.perform(
            post(
                "/v1/accounts"
            )
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", notNullValue()))
            .andDo {
                val response = it.response.contentAsString.jsonToObject(AccountResponse::class.java)
                assertNotNull(response)
                assertEquals(request.customerId, response.customerId)
                assertEquals(request.initialBalance?.amount, response.balance?.amount)
                assertEquals(request.initialBalance?.scale, response.balance?.scale)
                assertEquals(request.initialBalance?.currency, response.balance?.currency)
            }
    }

    @Test
    fun `should find account`() {
        val account = requestToCreateAccount()

        this.mockMvc.perform(
            get(
                "/v1/accounts/{id}", account.id
            )
        )
            .andExpect(status().isOk)
            .andDo {
                val response = it.response.contentAsString.jsonToObject(AccountResponse::class.java)
                assertNotNull(response)
                assertEquals(account.id, response.id)
                assertEquals(account.customerId, response.customerId)
                assertEquals(account.balance?.amount, response.balance?.amount)
                assertEquals(account.balance?.scale, response.balance?.scale)
                assertEquals(account.balance?.currency, response.balance?.currency)
            }
    }

    @Test
    fun `should perform credit in account`() {
        val account = requestToCreateAccount()

        val request = buildCreateCreditRequest()

        //Perform credit
        this.mockMvc.perform(
            post(
                "/v1/accounts/{id}/credits", account.id
            )
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
            .andDo {
                val response = it.response.contentAsString.jsonToObject(CreditResponse::class.java)
                assertNotNull(response)
                assertEquals(account.id, response.accountId)
                assertEquals(request.value?.amount, response.value?.amount)
                assertEquals(request.value?.scale, response.value?.scale)
                assertEquals(request.value?.currency, response.value?.currency)
                assertEquals(request.transactionId, response.transactionId)
                assertEquals(request.origin, response.origin)
                assertEquals(request.description, response.description)
                assertEquals(PaymentType.CREDIT.name, response.type)
            }

        //Validate new balance
        this.mockMvc.perform(
            get(
                "/v1/accounts/{id}", account.id
            )
        )
            .andExpect(status().isOk)
            .andDo {
                val response = it.response.contentAsString.jsonToObject(AccountResponse::class.java)
                val balance = (account.balance?.amount!! + request.value?.amount!!)
                assertNotNull(response)
                assertEquals(account.id, response.id)
                assertEquals(account.customerId, response.customerId)
                assertEquals(balance, response.balance?.amount)
                assertEquals(account.balance?.scale, response.balance?.scale)
                assertEquals(account.balance?.currency, response.balance?.currency)
            }
    }
}