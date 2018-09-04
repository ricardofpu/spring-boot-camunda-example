package br.com.camunda.example.web.controller.v1

import br.com.camunda.example.api.v1.response.CustomerResponse
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
}