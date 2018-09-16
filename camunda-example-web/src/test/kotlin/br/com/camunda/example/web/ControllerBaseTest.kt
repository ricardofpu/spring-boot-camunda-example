package br.com.camunda.example.web

import br.com.camunda.example.api.v1.request.CreateAccountRequest
import br.com.camunda.example.api.v1.request.CreateCreditRequest
import br.com.camunda.example.api.v1.request.CreateCustomerRequest
import br.com.camunda.example.api.v1.request.CreatePaymentRequest
import br.com.camunda.example.api.v1.request.UpdateCustomerRequest
import br.com.camunda.example.api.v1.response.AccountResponse
import br.com.camunda.example.api.v1.response.CustomerResponse
import br.com.camunda.example.domain.randomUUID
import br.com.camunda.example.infrastructure.jsonToObject
import br.com.camunda.example.infrastructure.objectToJson
import br.com.camunda.example.web.config.ApplicationConfigTest
import capital.scalable.restdocs.AutoDocumentation
import capital.scalable.restdocs.SnippetRegistry
import capital.scalable.restdocs.jackson.JacksonResultHandlers
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors
import capital.scalable.restdocs.section.SectionSnippet
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.http.MediaType
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.cli.CliDocumentation
import org.springframework.restdocs.http.HttpDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.PostConstruct

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [(ApplicationConfigTest::class)])
@Sql(
    scripts = ["classpath:sqlScripts/clear_tables.sql"],
    config = SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED),
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
abstract class ControllerBaseTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    protected lateinit var mockMvc: MockMvc

    @get:Rule
    var restDocumentation = JUnitRestDocumentation()

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private val messageSource: MessageSource? = null

    private var accessor: MessageSourceAccessor? = null

    @PostConstruct
    private fun init() {
        accessor = MessageSourceAccessor(messageSource, Locale.US)
    }

    fun get(code: String): List<String> = listOf(accessor!!.getMessage(code))

    @Before
    fun setUp() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .alwaysDo<DefaultMockMvcBuilder>(JacksonResultHandlers.prepareJackson(objectMapper))
            .alwaysDo<DefaultMockMvcBuilder>(commonDocumentation())

            .apply<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
                    .uris()
                    .and().snippets()
                    .withDefaults(
                        CliDocumentation.curlRequest(),
                        HttpDocumentation.httpRequest(),
                        HttpDocumentation.httpResponse(),
                        AutoDocumentation.requestFields(),
                        AutoDocumentation.responseFields(),
                        AutoDocumentation.pathParameters(),
                        AutoDocumentation.requestParameters(),
                        AutoDocumentation.description(),
                        AutoDocumentation.methodAndPath(),
                        buildSection()
                    )
            )
            .build()
    }

    private fun commonDocumentation(): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
            "{class-name}/{method-name}",
            Preprocessors.preprocessRequest(
                ResponseModifyingPreprocessors.replaceBinaryContent(),
                ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                Preprocessors.prettyPrint()
            ),
            Preprocessors.preprocessResponse(
                ResponseModifyingPreprocessors.replaceBinaryContent(),
                ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                Preprocessors.prettyPrint()
            )
        )
    }

    private fun buildSection(): SectionSnippet {
        return AutoDocumentation.sectionBuilder()
            .snippetNames(
                SnippetRegistry.PATH_PARAMETERS,
                SnippetRegistry.HTTP_REQUEST,
                SnippetRegistry.REQUEST_PARAMETERS,
                SnippetRegistry.REQUEST_FIELDS,
                SnippetRegistry.HTTP_RESPONSE,
                SnippetRegistry.RESPONSE_FIELDS
            )
            .skipEmpty(true)
            .build()
    }

    protected fun requestToCreateCustomer(
        request: CreateCustomerRequest = buildCreateCustomerRequest()
    ): CustomerResponse {
        val response = this.mockMvc.perform(
            post(
                "/v1/customers"
            )
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", CoreMatchers.notNullValue()))
            .andReturn()

        return response.response.contentAsString.jsonToObject(CustomerResponse::class.java)
    }

    protected fun requestToCreateAccount(
        customerRequest: CreateCustomerRequest = buildCreateCustomerRequest()
    ): AccountResponse {

        val customer = requestToCreateCustomer(customerRequest)

        val request = buildCreateAccountRequest(customerId = customer.id!!)

        val response = this.mockMvc.perform(
            post(
                "/v1/accounts"
            )
                .content(request.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", CoreMatchers.notNullValue()))
            .andReturn()

        return response.response.contentAsString.jsonToObject(AccountResponse::class.java)
    }

    protected fun buildCreateCustomerRequest() =
        CreateCustomerRequest(
            fullName = "Ricardo Borges",
            nickName = "Ricardo",
            gender = "MALE",
            phoneNumber = "3499999999",
            email = "ricardo@test.com",
            birthDate = SimpleDateFormat("yyyy-MM-dd").parse("1992-06-29")
        )

    protected fun buildUpdateCustomerRequest(
        fullName: String = "Ricardo Borges",
        nickName: String = "Ricardo",
        gender: String = "MALE",
        phoneNumber: String = "3499999999",
        email: String = "ricardo@test.com",
        birthDate: Date = SimpleDateFormat("yyyy-MM-dd").parse("1992-06-29")
    ): UpdateCustomerRequest =
        UpdateCustomerRequest(
            fullName = fullName,
            nickName = nickName,
            gender = gender,
            phoneNumber = phoneNumber,
            email = email,
            birthDate = birthDate
        )

    protected fun buildCreateAccountRequest(
        customerId: String = randomUUID(),
        initialBalance: CreateAccountRequest.Balance = CreateAccountRequest.Balance(
            amount = 1000,
            scale = 2,
            currency = "BRL"
        )
    ): CreateAccountRequest =
        CreateAccountRequest(
            customerId = customerId,
            initialBalance = initialBalance
        )

    protected fun buildCreateCreditRequest(): CreateCreditRequest =
        CreateCreditRequest(
            transactionId = randomUUID(),
            description = "CREDIT",
            origin = "ORIGIN_APP",
            value = CreateCreditRequest.Value(
                amount = 2000,
                scale = 2,
                currency = "BRL"
            )
        )

    protected fun buildCreatePaymentRequest() =
        CreatePaymentRequest(
            payment = CreatePaymentRequest.Payment(
                amount = 1000,
                scale = 2,
                currency = "BRL"
            ),
            type = "CREDIT",
            transactionType = "PAYMENT"
        )

}