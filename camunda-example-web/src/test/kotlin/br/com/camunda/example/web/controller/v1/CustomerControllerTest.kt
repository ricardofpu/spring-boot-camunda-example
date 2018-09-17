package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.response.CustomerResponse
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.infrastructure.jsonToObject
import br.com.camunda.example.infrastructure.objectToJson
import br.com.camunda.example.web.ControllerBaseTest
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CustomerControllerTest : ControllerBaseTest() {

    @Test
    fun `should create customer`() {
        val request = buildCreateCustomerRequest()

        this.mockMvc.perform(
            post(
                "/v1/customers"
            )
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", notNullValue()))
            .andDo {
                val response = it.response.contentAsString.jsonToObject(CustomerResponse::class.java)
                assertNotNull(response)
                assertEquals(request.fullName, response.fullName)
                assertEquals(request.nickName, response.nickName)
                assertEquals(request.gender, response.gender)
                assertEquals(request.phoneNumber, response.phoneNumber)
                assertEquals(request.email, response.email)
                assertEquals(request.birthDate, response.birthDate)
            }
    }

    @Test
    fun `should find customer`() {
        val customer = requestToCreateCustomer()

        this.mockMvc.perform(
            get(
                "/v1/customers/{id}", customer.id
            )
        )
            .andExpect(status().isOk)
            .andDo {
                val response = it.response.contentAsString.jsonToObject(CustomerResponse::class.java)
                assertNotNull(response)
                assertEquals(customer.fullName, response.fullName)
                assertEquals(customer.nickName, response.nickName)
                assertEquals(customer.gender, response.gender)
                assertEquals(customer.phoneNumber, response.phoneNumber)
                assertEquals(customer.email, response.email)
                assertEquals(customer.birthDate, response.birthDate)
            }
    }

    @Test
    fun `shouldn't find customer when he not exists`() {
        val id = randomUUID()

        this.mockMvc.perform(
            get(
                "/v1/customers/{id}", id
            )
        )
            .andExpect(status().isNotFound)
    }

    @Test
    fun `should update customer`() {
        val customer = requestToCreateCustomer()

        val request = buildUpdateCustomerRequest(
            fullName = "Ricardo M Borges",
            phoneNumber = "348888888",
            email = "ricardom@email.com",
            birthDate = customer.birthDate!!
        )

        this.mockMvc.perform(
            put(
                "/v1/customers/{id}", customer.id
            )
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isOk)
            .andDo {
                val response = it.response.contentAsString.jsonToObject(CustomerResponse::class.java)
                assertNotNull(response)
                assertEquals(request.fullName, response.fullName)
                assertEquals(request.nickName, response.nickName)
                assertEquals(request.gender, response.gender)
                assertEquals(request.phoneNumber, response.phoneNumber)
                assertEquals(request.email, response.email)
                assertEquals(request.birthDate, response.birthDate)
            }
    }

    @Test
    fun `should delete customer`() {
        val customer = requestToCreateCustomer()

        this.mockMvc.perform(
            delete(
                "/v1/customers/{id}", customer.id
            )
        )
            .andExpect(status().isOk)
    }

}